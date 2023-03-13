package com.example.service

import com.example.datamodel.day.StudentDay
import com.example.datamodel.day.StudentDayRequest
import com.example.datamodel.day.TeacherDay
import com.example.datamodel.day.TeacherDayRequest
import com.example.datamodel.lesson.LessonInfo
import com.example.datamodel.lesson.student.StudentLessonList
import com.example.datamodel.lesson.teacher.TeacherLessonList
import com.example.datasource.LessonDataRepository
import java.util.logging.Logger


class LessonService(
    private val lessonDataRepository: LessonDataRepository
) {
    private val log: Logger = Logger.getLogger("LessonService")

    /**
     *  Возвращает информацию об уроке
     *
     *  @param lessonId
     *  @return LessonInfo
     */
    fun getLesson(lessonId: String): LessonInfo {
        log.info("getLesson: $lessonId")

        return lessonDataRepository.getLessonInfo(lessonId)
    }

    /**
     * Gолучает dayId и возвращает StudentDay
     *
     * @param studentDayRequest
     * @return StudentDay
     */
    fun getStudentDay(studentDayRequest: StudentDayRequest): StudentDay {
        log.info("getLesson: $studentDayRequest")


        val dayId = lessonDataRepository.getStudentDayId(studentDayRequest)


        log.info("getLesson: $dayId")
        return StudentDay(
            dayId = dayId,
            date = studentDayRequest.date,
            classId = studentDayRequest.classId)
    }

    /**
     * Получает dayId и schoolId, возвращает StudentDay
     *
     * @param teacherDayRequest
     * @return TeacherDay
     */
    fun getTeacherDay(teacherDayRequest: TeacherDayRequest): TeacherDay {
        log.info("getLesson: $teacherDayRequest")


        val params = lessonDataRepository.getTeacherDayParams(teacherDayRequest)
        val dayId = params[0]
        val schoolId = params[1]


        log.info("getLesson: $dayId, $schoolId")
        return TeacherDay(
            teacherDayId = dayId,
            date = teacherDayRequest.date,
            schoolId = schoolId,
            teacherId = teacherDayRequest.teacherId
        )
    }

    /**
     * Достаёт из бд список уроков на день для учителя
     *
     * @param teacherDay
     * @return TeacherLessonList
     */
    fun getTeacherLessonList(teacherDay: TeacherDay): TeacherLessonList {
        log.info("getLesson: $teacherDay")

        return lessonDataRepository.getTeacherLessons(teacherDay)
    }

    /**
     * Достаёт из бд список уроков на день для ученика
     *
     * @param studentDay
     * @return StudentLessonList
     */
    fun getStudentLessonList(studentDay: StudentDay): StudentLessonList {
        log.info("getLesson: $studentDay")

        return lessonDataRepository.getStudentLessons(studentDay)
    }
}