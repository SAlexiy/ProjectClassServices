package com.example

import com.example.features.loginRouting
import com.example.features.registerRouting
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import com.example.userauthservice.datasource.GetAccessToken
import com.example.userauthservice.datasource.UserDataRepository
import com.example.userauthservice.datasource.YDBConnection
import com.example.service.LoginService
import com.example.service.RegisterService

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    val ydbConnection = YDBConnection(GetAccessToken())

    val userDataRepository = UserDataRepository(ydbConnection)

    val loginService = LoginService(userDataRepository)
    val registerService = RegisterService(userDataRepository)


    configureSecurity()
    configureSerialization()
    configureRouting()

    loginRouting(loginService)
    registerRouting(registerService)
}
