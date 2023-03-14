package com.example.datamodel.lesson.teacher

import kotlinx.serialization.Serializable

@Serializable
data class TeacherLessonShortInfo(
    val name: String,
    val lessonId: String,
    val startTime: String,
    val endTime: String,
    val classRoom: String,
    var className: String,
    var classGrade: Int
)
