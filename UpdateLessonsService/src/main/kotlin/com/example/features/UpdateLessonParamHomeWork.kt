package com.example.features

import com.example.datamodel.lesson.LessonParamsHomeWork
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
 * изменяет домашнее задание урока
 *
 * @param LessonParamsHomeWork
 */
fun Application.updateLessonParamHomeWork(lessonService: LessonService) {
    val log: Logger = Logger.getLogger("Application.updateLessonParamHomeWork")

    routing {
        post("/lessons/update/lesson/params/homework") {

            val homework = call.receive<LessonParamsHomeWork>()
            log.info("$homework")

            lessonService.updateLessonParamsHomeWork(homework)

            call.respond(
                status = HttpStatusCode.OK,
                message = ""
            )
        }
    }
}
