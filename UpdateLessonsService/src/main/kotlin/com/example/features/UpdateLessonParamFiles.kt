package com.example.features

import com.example.datamodel.lesson.LessonParamsFiles
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
 * изменяет файлы урока
 *
 * @param LessonParamsFiles
 */
fun Application.updateLessonParamFiles(lessonService: LessonService) {
    val log: Logger = Logger.getLogger("Application.updateLesson")

    routing {
        post("/lessons/update/lesson/params/files") {

            val files = call.receive<LessonParamsFiles>()
            log.info(files.toString())

            lessonService.updateLessonParamsFiles(files)

            call.respond(
                status = HttpStatusCode.OK,
                message = ""
            )
        }
    }
}
