package com.example.datasource

import com.example.datamodel.lesson.*
import com.example.datasource.user_data.*
import com.example.userauthservice.datasource.YDBConnection
import java.util.logging.Logger

class LessonDataRepository(
    private val ydbConnection: YDBConnection
) : LessonDataRepositoryInterface {

    private val log = Logger.getLogger("LessonDataRepository")

    private val updateLessonInfo = UpdateLessonInfo(ydbConnection)

    override fun updateLesson(lessonInfo: LessonInfo) {
        log.info("updateStudentLessons: $lessonInfo")

        updateLessonInfo.updateLesson(lessonInfo)
    }

    override fun updateLessonParams(lessonParams: LessonParams) {
        log.info("updateStudentLessonParams: $lessonParams")

        updateLessonInfo.updateLessonParams(lessonParams)
    }

    override fun updateLessonParamsHomeWork(homework: LessonParamsHomeWork) {
        log.info("updateStudentLessonParamsHomeWork: $homework")

        updateLessonInfo.updateLessonParamsHomeWork(homework)
    }

    override fun updateLessonParamsFiles(files: LessonParamsFiles) {
        log.info("updateStudentLessonParamsFiles: $files")

        updateLessonInfo.updateLessonParamsFiles(files)
    }

    override fun updateLessonParamsTheme(theme: LessonParamsTheme) {
        log.info("updateLessonParamsTheme: $theme")

        updateLessonInfo.updateLessonParamsTheme(theme)
    }
}
