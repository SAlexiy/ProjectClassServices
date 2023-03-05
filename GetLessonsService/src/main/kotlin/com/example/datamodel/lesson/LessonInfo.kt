package com.example.datamodel.lesson

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LessonInfo(

    @SerialName("lesson_id")
    var lessonId: String = "",

    @SerialName("start_time")
    var startTime: String,

    @SerialName("end_time")
    var endTime: String,

    val name: String,

    @SerialName("class_room")
    val classRoom: String,

    @SerialName("homework")
    val homeWork: String,

    @SerialName("theme")
    val theme: String,

    val files: MutableList<String>
)
