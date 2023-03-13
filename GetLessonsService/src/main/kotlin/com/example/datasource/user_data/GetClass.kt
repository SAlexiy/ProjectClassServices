package com.example.datasource.user_data

import com.example.userauthservice.datasource.YDBConnection
import tech.ydb.table.Session
import tech.ydb.table.query.DataQueryResult
import tech.ydb.table.transaction.TxControl
import java.util.logging.Logger


class GetClass (
    private val ydbConnection: YDBConnection
) {
    private val log: Logger = Logger.getLogger("GetClass")

    /**
     * Возвращает массив String
     * 1 значение grade
     * 2 начение class
     *
     *
     */
    fun getClassName(dayId: String): MutableList<String> {
        log.info("getClassName: $dayId")


        val txControl: TxControl<*> = TxControl.serializableRw().setCommitTx(true)
        val query = ("SELECT `student_day_id`, `class`, `grade`\n" +
                "FROM `lesson/student_day` \n" +
                "INNER JOIN `school/class` ON `school/class`.`class_id` = `lesson/student_day`.`class_id`\n" +
                "WHERE `student_day_id` = \"$dayId\"")


        val result: DataQueryResult =
            ydbConnection.retryCtx.supplyResult { session: Session ->
                session.executeDataQuery(
                    query,
                    txControl
                )
            }.join().value


        var className: MutableList<String> = mutableListOf()

        val rs = result.getResultSet(0)
        while (rs.next()) {
            className.add("${rs.getColumn("grade").uint8}")
            className.add(rs.getColumn("class").text)
        }
        log.info("getClassName: $className")


        return className
    }
}
