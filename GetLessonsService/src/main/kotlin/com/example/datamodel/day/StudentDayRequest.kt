package com.example.datamodel.day

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StudentDayRequest(

    val date: String,

    @SerialName("class_id")
    val classId: String
)
