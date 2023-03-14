package com.example.features

import com.example.datamodel.lesson.LessonParamsTheme
import com.example.service.LessonService
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import java.util.logging.Logger

/**
 * post запрос
 *
 * изменяет тему урока
 *
 * @param LessonParamsTheme
 */
fun Application.updateLessonParamTheme(lessonService: LessonService) {
    val log: Logger = Logger.getLogger("Application.updateLessonParamTheme")

    routing {
        post("/lessons/update/lesson/params/theme") {

            val theme = call.receive<LessonParamsTheme>()
            log.info("$theme")

            lessonService.updateLessonParamsTheme(theme)

            call.respond(
                status = HttpStatusCode.OK,
                message = ""
            )
        }
    }
}
