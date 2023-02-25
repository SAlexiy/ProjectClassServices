package com.example.features

import com.example.userauthservice.datamodel.user.UserReg
import com.example.userauthservice.service.RegisterService
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.util.logging.Logger

fun Application.registerRouting(
    registerService: RegisterService
) {
    val log = Logger.getLogger("Application.registerRouting")

    routing {
        post("/user/auth/register") {
            val userReg = call.receive<UserReg>(UserReg::class)

            log.info("UserAuthController register: ${userReg.login}, ${userReg.password}")

            if(registerService.register(userReg)){
                log.info("UserAuthController login: true")

                call.respond(
                    status = HttpStatusCode.Created,
                    message = "Аккаунт создан"
                )

            } else{
                log.info("UserAuthController login: false")

                call.respond(
                    status = HttpStatusCode.Conflict,
                    message = "Логин занят"
                )
            }
        }
    }
}
