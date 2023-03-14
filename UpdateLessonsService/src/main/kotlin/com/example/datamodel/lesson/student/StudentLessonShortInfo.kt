package com.example.datamodel.lesson.student

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StudentLessonShortInfo(

    @SerialName("lesson_id")
    var lessonId: String = "",

    @SerialName("start_time")
    var startTime: String,

    @SerialName("end_time")
    var endTime: String,

    val name: String,

    @SerialName("homework")
    val homeWork: String,
)
