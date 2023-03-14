package com.example.service

import com.example.datamodel.lesson.*
import com.example.datasource.LessonDataRepository
import java.util.logging.Logger


class LessonService(
    private val lessonDataRepository: LessonDataRepository
) {
    private val log: Logger = Logger.getLogger("LessonService")



    /**
     * Изменяет информацию об уроке
     *
     * @param lessonInfo
     */
    fun updateLesson(lessonInfo: LessonInfo) {
        log.info("updateLesson: $lessonInfo")

        lessonDataRepository.updateLesson(lessonInfo)

    }

    fun updateLessonParams(lessonParams: LessonParams) {
        log.info("updateLesson: $lessonParams")

        lessonDataRepository.updateLessonParams(lessonParams)
    }

    fun updateLessonParamsHomeWork(homework: LessonParamsHomeWork) {
        log.info("updateLesson: $homework")

        lessonDataRepository.updateLessonParamsHomeWork(homework)
    }

    fun updateLessonParamsFiles(files: LessonParamsFiles) {
        log.info("updateLesson: $files")

        lessonDataRepository.updateLessonParamsFiles(files)
    }

    fun updateLessonParamsTheme(theme: LessonParamsTheme) {
        log.info("updateLesson: $theme")

        lessonDataRepository.updateLessonParamsTheme(theme)

    }
}