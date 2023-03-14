package com.example.datamodel.lesson

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LessonParamsTheme(

    @SerialName("lesson_id")
    var lessonId: String,

    val theme: String
)
