package com.example.features

import com.example.datamodel.day.TeacherDayRequest
import com.example.datamodel.lesson.teacher.TeacherLessonList
import com.example.service.LessonService
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import java.util.logging.Logger

/**
 * get запрос
 *
 * получает TeacherDayRequest, возвращает TeacherLessonList (список предметов для учителя с краткой информацией)
 */
fun Application.getTeacherLessonList(lessonService: LessonService) {
    val log: Logger = Logger.getLogger("Application.getTeacherDay")

    routing {
        get("/lessons/get/teacher/lesson_list") {

            val teacherDayRequest = call.receive<TeacherDayRequest>()
            log.info("$teacherDayRequest")


            val teacherDay = lessonService.getTeacherDay(
                teacherDayRequest
            )
            log.info("$teacherDay")


            call.respond<TeacherLessonList>(
                lessonService.getTeacherLessonList(teacherDay)
            )

        }
    }
}
