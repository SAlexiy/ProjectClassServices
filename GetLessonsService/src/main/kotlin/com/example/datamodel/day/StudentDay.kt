package com.example.datamodel.day

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StudentDay(

    @SerialName("student_day_id")
    val dayId: String,

    val date: String,

    @SerialName("class_id")
    val classId: String
)
