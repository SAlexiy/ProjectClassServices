package com.example.features

import com.example.datamodel.lesson.LessonParams
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
 * изменяет тему, домашнее задание и файлы урока
 *
 * @param LessonParams
 */
fun Application.updateLessonParams(lessonService: LessonService) {
    val log: Logger = Logger.getLogger("Application.updateLesson")

    routing {
        post("/lessons/update/lesson/params") {

            val lessonParams = call.receive<LessonParams>()
            log.info(lessonParams.toString())

            lessonService.updateLessonParams(lessonParams)

            call.respond(
                status = HttpStatusCode.OK,
                message = ""
            )
        }
    }
}
