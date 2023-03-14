package com.example.datamodel.day

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TeacherDay(

    @SerialName("teacher_day_id")
    val teacherDayId: String,

    @SerialName("school_id")
    val schoolId: String,

    @SerialName("teacher_id")
    val teacherId: String,

    val date: String
)
