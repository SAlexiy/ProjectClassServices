package com.example.datamodel.lesson.teacher

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TeacherLessonList(

    @SerialName("lesson_list")
    val lessonList: MutableList<TeacherLessonShortInfo>
)
