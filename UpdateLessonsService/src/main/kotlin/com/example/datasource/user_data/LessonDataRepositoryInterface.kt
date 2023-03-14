package com.example.datasource.user_data

import com.example.datamodel.lesson.*

interface LessonDataRepositoryInterface {
    fun updateLesson(lessonInfo: LessonInfo)
    fun updateLessonParams(lessonParams: LessonParams)
    fun updateLessonParamsHomeWork(homework: LessonParamsHomeWork)
    fun updateLessonParamsFiles(files: LessonParamsFiles)
    fun updateLessonParamsTheme(theme: LessonParamsTheme)
}

