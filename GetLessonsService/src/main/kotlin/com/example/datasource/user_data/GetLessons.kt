package com.example.datasource.user_data

import com.example.datamodel.lesson.student.StudentLessonShortInfo
import com.example.datamodel.lesson.teacher.TeacherLessonShortInfo
import com.example.userauthservice.datasource.YDBConnection
import tech.ydb.table.Session
import tech.ydb.table.query.DataQueryResult
import tech.ydb.table.transaction.TxControl
import java.util.function.Function

class GetLessons(
    private val ydbConnection: YDBConnection
) {

    /**
     *
     */
    fun getStudentLessons(studentDayId: String): MutableList<StudentLessonShortInfo> {

        val txControl: TxControl<*> = TxControl.serializableRw().setCommitTx(true)
        val query = ("SELECT `lesson_id`, `end_time`, `homework`, `name`, `start_time`\n" +
                "FROM `lesson/lessons`\n" +
                "WHERE `student_day_id` = \"$studentDayId\";")


        val result: DataQueryResult =
            ydbConnection.retryCtx.supplyResult<DataQueryResult>(Function { session: Session ->
                session.executeDataQuery(
                    query,
                    txControl
                )
            }).join().value


        val lessonList: MutableList<StudentLessonShortInfo> = mutableListOf()

        val rs = result.getResultSet(0)
        while (rs.next()) {
            lessonList.add(
                StudentLessonShortInfo(
                    lessonId = rs.getColumn("lesson_id").text,
                    startTime = rs.getColumn("start_time").text,
                    endTime = rs.getColumn("end_time").text,
                    name = rs.getColumn("name").text,
                    homeWork = rs.getColumn("homework").text
                )
            )
        }

        return lessonList
    }


    /**
     *
     */
    fun getTeacherLessons(teacherDayId: String): MutableList<TeacherLessonShortInfo> {
        val txControl: TxControl<*> = TxControl.serializableRw().setCommitTx(true)
        val query = ("SELECT `lesson_id`, `class_room`, `end_time`, `name`, `start_time`, `student_day_id`, `theme`\n" +
                "FROM `lesson/lessons`" +
                "WHERE `teacher_day_id` = \"$teacherDayId\"\n;")


        val result: DataQueryResult =
            ydbConnection.retryCtx.supplyResult { session: Session ->
                session.executeDataQuery(
                    query,
                    txControl
                )
            }.join().value


        val lessonList: MutableList<TeacherLessonShortInfo> = mutableListOf()

        val rs = result.getResultSet(0)
        while (rs.next()) {
            lessonList.add(
                TeacherLessonShortInfo(
                    lessonId = rs.getColumn("lesson_id").text,
                    startTime = rs.getColumn("start_time").text,
                    endTime = rs.getColumn("end_time").text,
                    name = rs.getColumn("name").text,
                    classRoom = rs.getColumn("class_room").text,
                    className = rs.getColumn("student_day_id").text,
                    classGrade = 0
                )
            )
        }

        return lessonList
    }
}