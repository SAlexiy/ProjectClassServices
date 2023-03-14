package com.example.datamodel.lesson.student

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StudentLessonList(

    @SerialName("lesson_list")
    val lessonList: MutableList<StudentLessonShortInfo>
)