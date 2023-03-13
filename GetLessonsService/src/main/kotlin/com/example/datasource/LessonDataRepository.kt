package com.example.datasource

import com.example.datamodel.day.StudentDay
import com.example.datamodel.day.StudentDayRequest
import com.example.datamodel.day.TeacherDay
import com.example.datamodel.day.TeacherDayRequest
import com.example.datamodel.lesson.LessonInfo
import com.example.datamodel.lesson.student.StudentLessonList
import com.example.datamodel.lesson.teacher.TeacherLessonList
import com.example.datasource.user_data.*
import com.example.userauthservice.datasource.YDBConnection
import java.util.logging.Logger

class LessonDataRepository(
    private val ydbConnection: YDBConnection
) : LessonDataRepositoryInterface {

    private val log = Logger.getLogger("LessonDataRepository")

    private val getClass = GetClass(ydbConnection)
    private val getDay = GetDay(ydbConnection)
    private val getLessonInfo = GetLessonInfo(ydbConnection)
    private val getLessons = GetLessons(ydbConnection)


    override fun getLessonInfo(lessonId: String): LessonInfo {
        val lessonInfo = getLessonInfo.getLesson(lessonId)

        if (lessonInfo != null){
            return lessonInfo
        } else{
            throw Exception("Have no lesson info")
        }
    }


    override fun getStudentLessons(studentDay: StudentDay): StudentLessonList {
        log.info("getStudentLessons: $studentDay")

        return StudentLessonList(getLessons.getStudentLessons(studentDay.dayId))
    }

    override fun getTeacherLessons(teacherDay: TeacherDay): TeacherLessonList {
        log.info("getTeacherLessons: $teacherDay")

        val lessonList = getLessons.getTeacherLessons(teacherDay.teacherDayId)

        for (i in 0 until lessonList.size){
            val className = getClass.getClassName(lessonList[i].className)
            lessonList[i].className = className[1]
            lessonList[i].classGrade = className[0].toInt()
        }

        return TeacherLessonList(lessonList)
    }


    override fun getStudentDayId(studentDayRequest: StudentDayRequest): String {
        log.info("getStudentDayId: $studentDayRequest")

        return getDay.getStudentDayId(studentDayRequest)!!
    }

    override fun getTeacherDayParams(teacherDayRequest: TeacherDayRequest): MutableList<String> {
        log.info("getTeacherDayParams: $teacherDayRequest")

        return getDay.getTeacherDayId(teacherDayRequest)
    }
}
