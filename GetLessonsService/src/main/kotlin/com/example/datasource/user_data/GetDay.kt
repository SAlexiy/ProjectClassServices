package com.example.datasource.user_data

import com.example.datamodel.day.StudentDayRequest
import com.example.datamodel.day.TeacherDayRequest
import com.example.userauthservice.datasource.YDBConnection
import tech.ydb.table.Session
import tech.ydb.table.query.DataQueryResult
import tech.ydb.table.transaction.TxControl
import java.util.logging.Logger

class GetDay (
    private val ydbConnection: YDBConnection
) {
    private val log: Logger = Logger.getLogger("GetDay")


    fun getStudentDayId(studentDayRequest: StudentDayRequest): String? {
        log.info("getStudentDayId: $studentDayRequest")


        val txControl: TxControl<*> = TxControl.serializableRw().setCommitTx(true)
        val query = ("SELECT `student_day_id`\n" +
                "FROM `lesson/student_day`" +
                "WHERE `class_id` = \"${studentDayRequest.classId}\";")


        val result: DataQueryResult =
            ydbConnection.retryCtx.supplyResult { session: Session ->
                session.executeDataQuery(
                    query,
                    txControl
                )
            }.join().value


        var dayId: String? = null
        val rs = result.getResultSet(0)
        while (rs.next())
        {
            dayId = rs.getColumn("student_day_id").text
        }
        log.info("getStudentDayId: $dayId")

        return dayId
    }


    /**
     * первый элемент teacher_day_id, второй school_id
     */
    fun getTeacherDayId(teacherDayRequest: TeacherDayRequest): MutableList<String> {
        log.info("getTeacherDayId: $teacherDayRequest")


        val txControl: TxControl<*> = TxControl.serializableRw().setCommitTx(true)
        val query = ("SELECT `teacher_day_id`, `school_id` \n" +
                "FROM `lesson/teacher_day`" +
                "WHERE `teacher_id` = \"${teacherDayRequest.teacherId}\" " +
                "AND `date` = CAST(\"${teacherDayRequest.date}\" AS Date);")


        val result: DataQueryResult =
            ydbConnection.retryCtx.supplyResult { session: Session ->
                session.executeDataQuery(
                    query,
                    txControl
                )
            }.join().value


        var params: MutableList<String> = mutableListOf()
        val rs = result.getResultSet(0)
        while (rs.next())
        {
            params.add(rs.getColumn("teacher_day_id").text)
            params.add(rs.getColumn("school_id").text)
        }
        log.info("getTeacherDayId: $params")

        return params
    }
}