val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val yandex_cloud: String by project

plugins {
    kotlin("jvm") version "1.8.10"
    id("io.ktor.plugin") version "2.2.3"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.10"
}

group = "com.example"
version = "1.0.0"
application {
    mainClass.set("com.example.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-auth-jvm:$ktor_version")

    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")


    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-client-serialization:$ktor_version")


    implementation("ch.qos.logback:logback-classic:$logback_version")


    implementation("tech.ydb:ydb-sdk-core:$yandex_cloud")
    implementation("tech.ydb.auth:yc-auth-provider:$yandex_cloud")
    implementation("tech.ydb:ydb-sdk-table:$yandex_cloud")

    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}


tasks{
    withType<Jar> {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE

        manifest {
            attributes["Main-Class"] = "com.example.ApplicationKt"
        }
        from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    }
    withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>{
        mergeServiceFiles()
    }
}


ktor {
    fatJar {
        archiveFileName.set("fat.jar")
    }

    docker {
        jreVersion.set(io.ktor.plugin.features.JreVersion.JRE_17)
        localImageName.set("sample-docker-image")
        imageTag.set("0.0.1-preview")
        portMappings.set(listOf(
            io.ktor.plugin.features.DockerPortMapping(
                80,
                8080,
                io.ktor.plugin.features.DockerPortMappingProtocol.TCP
            )
        ))

        externalRegistry.set(
            io.ktor.plugin.features.DockerImageRegistry.dockerHub(
                appName = provider { "ktor-sample1" },
                username = providers.environmentVariable("DOCKER_HUB_USERNAME"),
                password = providers.environmentVariable("DOCKER_HUB_PASSWORD")
            )
        )
    }
}
