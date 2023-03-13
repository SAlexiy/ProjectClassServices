package com.example.datasource.user_data

import com.example.datamodel.lesson.Files
import com.example.datamodel.lesson.LessonInfo
import com.example.userauthservice.datasource.YDBConnection
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import tech.ydb.table.Session
import tech.ydb.table.query.DataQueryResult
import tech.ydb.table.transaction.TxControl
import java.util.logging.Logger

class GetLessonInfo(
    private val ydbConnection: YDBConnection
) {
    private val log: Logger = Logger.getLogger("GetLessonInfo")


    fun getLesson(lessonId: String): LessonInfo? {
        log.info("getLesson: $lessonId")


        val txControl: TxControl<*> = TxControl.serializableRw().setCommitTx(true)
        val query = ("SELECT `lesson_id`, `class_room`, `end_time`," +
                "CAST(`files` AS Utf8) AS files," +
                "`homework`, `name`, `start_time`, `theme`" +
                "FROM `lesson/lessons`" +
                "WHERE `lesson_id` = \"$lessonId\";")


        val result: DataQueryResult =
            ydbConnection.retryCtx.supplyResult { session: Session ->
                session.executeDataQuery(
                    query,
                    txControl
                )
            }.join().value


        var lesson: LessonInfo? = null

        val rs = result.getResultSet(0)
        while (rs.next()) {

            lesson = LessonInfo(
                lessonId = rs.getColumn("lesson_id").text,
                startTime = rs.getColumn("start_time").text,
                endTime = rs.getColumn("end_time").text,
                name = rs.getColumn("name").text,
                homeWork = rs.getColumn("homework").text,
                classRoom = rs.getColumn("class_room").text,
                theme = rs.getColumn("theme").text,
                files = Json.decodeFromString<Files>(rs.getColumn("files").text).files
            )
        }
        log.info("getLesson: $lesson")


        return lesson
    }
}
