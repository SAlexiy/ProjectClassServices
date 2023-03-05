package com.example.datamodel.lesson

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Lesson(

    @SerialName("lesson_id")
    var lessonId: String,

    @SerialName("teacher_day_id")
    var teacherDayId: String,

    @SerialName("day_id")
    var dayId: String,
)
