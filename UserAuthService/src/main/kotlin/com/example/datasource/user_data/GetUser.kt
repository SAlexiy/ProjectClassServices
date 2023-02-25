package com.example.userauthservice.datasource.user_data

import com.example.userauthservice.datasource.YDBConnection
import tech.ydb.table.Session
import tech.ydb.table.query.DataQueryResult
import tech.ydb.table.transaction.TxControl
import java.util.function.Function


/**возвращает userAuth по логину
 *
 * если пользователь не найден, возвращает userAuth с пустыми строками*/
fun getUserAuthData(login: String, ydbConnection: YDBConnection): String {

    val txControl: TxControl<*> = TxControl.serializableRw().setCommitTx(true)
    val query = ("SELECT `password` " +
                "FROM `user/user_auth` " +
                "WHERE login = '$login';")

    val result: DataQueryResult = ydbConnection.retryCtx.supplyResult<DataQueryResult>(Function { session: Session ->
        session.executeDataQuery(
            query,
            txControl
        )
    }).join().value

    var password = ""

    val rs = result.getResultSet(0)
    while (rs.next()) {
        password = rs.getColumn("password").text
    }

    return password
}

fun isLoginFree(login: String, ydbConnection: YDBConnection): Boolean {
    val txControl: TxControl<*> = TxControl.serializableRw().setCommitTx(true)
    val query = ("SELECT `password` " +
            "FROM `user/user_auth` " +
            "WHERE login = '$login';")

    val result: DataQueryResult = ydbConnection.retryCtx.supplyResult<DataQueryResult>(Function { session: Session ->
        session.executeDataQuery(
            query,
            txControl
        )
    }).join().value

    val rs = result.getResultSet(0)
    while (rs.next()) {
        return rs.getColumn("password").text == null
    }

    return true

}