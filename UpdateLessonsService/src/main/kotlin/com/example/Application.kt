package com.example

import com.example.datasource.GetAccessToken
import com.example.datasource.LessonDataRepository
import com.example.features.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import com.example.service.LessonService
import com.example.userauthservice.datasource.YDBConnection

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {

    val ydbConnection = YDBConnection(GetAccessToken())
    val lessonDataRepository = LessonDataRepository(ydbConnection)
    val lessonService = LessonService(lessonDataRepository)

    configureSerialization()
    configureRouting()

    updateLesson(lessonService)
    updateLessonParams(lessonService)
    updateLessonParamFiles(lessonService)
    updateLessonParamTheme(lessonService)
    updateLessonParamHomeWork(lessonService)
}
