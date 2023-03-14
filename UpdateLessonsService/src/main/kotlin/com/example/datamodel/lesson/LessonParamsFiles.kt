package com.example.datamodel.lesson

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LessonParamsFiles(

    @SerialName("lesson_id")
    var lessonId: String,

    val files: MutableList<String>
)
