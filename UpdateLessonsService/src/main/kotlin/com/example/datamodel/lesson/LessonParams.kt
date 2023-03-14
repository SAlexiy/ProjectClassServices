package com.example.datamodel.lesson

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LessonParams(

    @SerialName("lesson_id")
    var lessonId: String,

    @SerialName("homework")
    val homeWork: String,

    val theme: String,

    val files: MutableList<String>
)
