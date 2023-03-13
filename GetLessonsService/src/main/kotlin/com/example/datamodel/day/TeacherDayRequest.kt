package com.example.datamodel.day

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TeacherDayRequest(

    @SerialName("teacher_id")
    val teacherId: String,

    val date: String
)
