package com.example.datasource.user_data

import com.example.datamodel.lesson.*
import com.example.userauthservice.datasource.YDBConnection
import tech.ydb.table.Session
import tech.ydb.table.query.DataQueryResult
import tech.ydb.table.transaction.TxControl
import java.util.logging.Logger

class UpdateLessonInfo (
    private val ydbConnection: YDBConnection
) {
    private val log: Logger = Logger.getLogger("UpdateLessonInfo")


    fun updateLesson(lessonInfo: LessonInfo) {
        log.info("updateLesson: $lessonInfo")

        val txControl: TxControl<*> = TxControl.serializableRw().setCommitTx(true)
        val query = ("UPDATE `lesson/lessons`\n" +

                "SET `class_room` = \"${lessonInfo.classRoom}\", `end_time` = \"${lessonInfo.endTime}\", " +
                "`name` = \"${lessonInfo.name}\", `start_time` = \"${lessonInfo.startTime}\", " +
                "`student_day_id` =  \"${lessonInfo.studentDayId}\", `teacher_day_id` =  \"${lessonInfo.teacherDayId}\"\n" +

                "WHERE `lesson_id` = \"${lessonInfo.lessonId}\";\n")


        val result: DataQueryResult =
            ydbConnection.retryCtx.supplyResult { session: Session ->
                session.executeDataQuery(
                    query,
                    txControl
                )
            }.join().value
    }

    fun updateLessonParams(lessonParams: LessonParams) {
        log.info("updateLessonParams: $lessonParams")

        val txControl: TxControl<*> = TxControl.serializableRw().setCommitTx(true)
        val query = ("UPDATE `lesson/lessons`" +

                "SET `files` = CAST(@@{ \"files\" : ${Files(lessonParams.files).toJsonString()} }@@ AS Json), " +
                "`homework` = \"${lessonParams.homeWork}\", `theme` = \"${lessonParams.theme}\" " +

                "WHERE `lesson_id` = \"${lessonParams.lessonId}\";")


        val result: DataQueryResult =
            ydbConnection.retryCtx.supplyResult { session: Session ->
                session.executeDataQuery(
                    query,
                    txControl
                )
            }.join().value
    }

    fun updateLessonParamsHomeWork(homework: LessonParamsHomeWork) {
        log.info("updateLessonParams: $homework")

        val txControl: TxControl<*> = TxControl.serializableRw().setCommitTx(true)
        val query = ("UPDATE `lesson/lessons`\n" +

                "SET `homework` = \"${homework.homeWork}\" \n" +

                "WHERE `lesson_id` = \"${homework.lessonId}\";\n")


        val result: DataQueryResult =
            ydbConnection.retryCtx.supplyResult { session: Session ->
                session.executeDataQuery(
                    query,
                    txControl
                )
            }.join().value
    }

    fun updateLessonParamsFiles(files: LessonParamsFiles) {
        log.info("updateLessonParams: $files")

        val txControl: TxControl<*> = TxControl.serializableRw().setCommitTx(true)
        val query = ("UPDATE `lesson/lessons` " +

                "SET `files` = CAST(@@{ \"files\" : ${Files(files.files).toJsonString()} }@@ AS Json) " +

                "WHERE `lesson_id` = \"${files.lessonId}\";")


        val result: DataQueryResult =
            ydbConnection.retryCtx.supplyResult { session: Session ->
                session.executeDataQuery(
                    query,
                    txControl
                )
            }.join().value
    }

    fun updateLessonParamsTheme(theme: LessonParamsTheme) {
        log.info("updateLessonParams: $theme")

        val txControl: TxControl<*> = TxControl.serializableRw().setCommitTx(true)
        val query = ("UPDATE `lesson/lessons`\n" +

                "SET `theme` = \"${theme.theme}\" \n" +

                "WHERE `lesson_id` = \"${theme.lessonId}\";\n")


        val result: DataQueryResult =
            ydbConnection.retryCtx.supplyResult { session: Session ->
                session.executeDataQuery(
                    query,
                    txControl
                )
            }.join().value
    }
}