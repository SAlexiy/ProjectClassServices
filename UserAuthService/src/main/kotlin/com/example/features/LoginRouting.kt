package com.example.features

import com.example.datamodel.user.UserLogin
import com.example.service.LoginService
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.util.logging.Logger

fun Application.loginRouting(
    loginService: LoginService
) {
    val log = Logger.getLogger("Application.loginRouting")

    routing {
        post("/user/auth/login") {
           val userLogin = call.receive<UserLogin>(UserLogin::class)


            log.info("UserAuthController login: ${userLogin.login}, ${userLogin.password}")

            val pas : String = loginService.login(userLogin.login)

            if(userLogin.password.hashCode().toString() == pas){
                log.info("UserAuthController login: true")

                call.respond(
                    status = HttpStatusCode.Accepted,
                    message = "Успешный вход"
                )

            } else{
                log.info("UserAuthController login: false")

                call.respond(
                    status = HttpStatusCode.NotAcceptable,
                    message = "Не успешный вход"
                )

            }
        }
    }
}
