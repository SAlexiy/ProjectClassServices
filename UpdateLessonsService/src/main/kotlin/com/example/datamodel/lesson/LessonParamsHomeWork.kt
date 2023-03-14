package com.example.datamodel.lesson

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LessonParamsHomeWork(

    @SerialName("lesson_id")
    var lessonId: String,

    @SerialName("homework")
    val homeWork: String
)
