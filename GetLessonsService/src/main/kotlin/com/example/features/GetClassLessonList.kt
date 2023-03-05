package com.example.features

import com.example.datamodel.day.StudentDayRequest
import com.example.datamodel.lesson.student.StudentLessonList
import com.example.service.LessonService
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import java.util.logging.Logger

/**
 * get запрос
 *
 * получает StudentDayRequest, возвращает StudentLessonList (список предметов для студента с краткой информацией)
 */
fun Application.getClassLessonList(lessonService: LessonService) {
    val log: Logger = Logger.getLogger("Application.getStudentLessonList")

    routing {
        get("/lessons/get/class/lesson_list") {

            val studentDayRequest = call.receive<StudentDayRequest>()
            log.info("$studentDayRequest")


            val studentDay = lessonService.getStudentDay(
                studentDayRequest
            )
            log.info("$studentDay")


            call.respond<StudentLessonList>(
                lessonService.getStudentLessonList(studentDay)
            )
        }
    }
}
