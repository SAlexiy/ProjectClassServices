package com.example.datamodel.lesson

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LessonInfo(

    @SerialName("lesson_id")
    var lessonId: String = "",

    @SerialName("teacher_day_id")
    var teacherDayId: String,

    @SerialName("student_day_id")
    var studentDayId: String,

    @SerialName("start_time")
    var startTime: String,

    @SerialName("end_time")
    var endTime: String,

    val name: String,

    @SerialName("class_room")
    val classRoom: String,

)
