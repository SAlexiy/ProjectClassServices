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
 * post запрос
 *
 * изменяет lesson_id, teacher_day_id, student_day_id, start_time,
 *              end_time, name, class_room, classRoom
 *
 * @param LessonInfo
 */
fun Application.updateLesson(lessonService: LessonService) {
    val log: Logger = Logger.getLogger("Application.updateLesson")

    routing {
        post("/lessons/update/lesson/info") {

            val lessonInfo = call.receive<LessonInfo>()
            log.info(lessonInfo.toString())

            lessonService.updateLesson(lessonInfo)

            call.respond(
                status = HttpStatusCode.OK,
                message = ""
            )
        }
    }
}
