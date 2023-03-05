package com.example.features

import com.example.datamodel.lesson.LessonInfo
import com.example.service.LessonService
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import java.util.logging.Logger

/**
 * get запрос
 *
 * получает lessonId, возвращает LessonInfo
 */
fun Application.getLesson(lessonService: LessonService) {
    val log: Logger = Logger.getLogger("Application.getLesson")

    routing {
        get("/lessons/get/lesson") {

            val lessonId = call.receive<String>()
            log.info(lessonId)

            try {
                val lessonInfo = lessonService.getLesson(lessonId)

                log.info("$lessonInfo")

                call.respond<LessonInfo>(
                    lessonInfo
                )
            } catch (e: Exception){

                log.info(e.message.toString())

                call.respond(
                    status = HttpStatusCode.NoContent,
                    message = e.message.toString()
                )
            }
        }
    }
}
