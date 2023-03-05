package com.example.datasource.user_data

import com.example.datamodel.day.StudentDay
import com.example.datamodel.day.StudentDayRequest
import com.example.datamodel.day.TeacherDay
import com.example.datamodel.day.TeacherDayRequest
import com.example.datamodel.lesson.LessonInfo
import com.example.datamodel.lesson.student.StudentLessonList
import com.example.datamodel.lesson.teacher.TeacherLessonList

interface LessonDataRepositoryInterface {

    /**
     * @param
     * @return
     */
    fun getLessonInfo(lessonId: String): LessonInfo

    /**
     * @param
     * @return
     */
    fun getStudentLessons(studentDay: StudentDay): StudentLessonList

    /**
     * @param
     * @return
     */
    fun getTeacherLessons(teacherDay: TeacherDay): TeacherLessonList

    /**
     * @param
     * @return
     */
    fun getStudentDayId(studentDayRequest: StudentDayRequest): String

    /**
     * @param
     * @return
     */
    fun getTeacherDayParams(teacherDayRequest: TeacherDayRequest): MutableList<String>
}

