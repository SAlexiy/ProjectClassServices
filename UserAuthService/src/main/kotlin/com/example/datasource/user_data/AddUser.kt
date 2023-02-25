package com.example.userauthservice.datasource.user_data

import com.example.datamodel.user.UserAuth
import com.example.datamodel.user.UserInfo
import com.example.userauthservice.datasource.YDBConnection
import tech.ydb.table.Session
import tech.ydb.table.transaction.TxControl

fun addUserData(userInfo: UserInfo, userAuth: UserAuth, ydbConnection: YDBConnection) {
    val txControl: TxControl<*> = TxControl.serializableRw().setCommitTx(true)

    var query =
        "UPSERT INTO `user/user_info`\n" +
                "    ( `user_id`, `birthday`, `email`, `first_name`, `last_name`, `number_phone`, `profile_photo`, `type` )\n" +
                "VALUES (\"${userInfo.userId}\", \"${userInfo.birthday}\", \"${userInfo.email}\", " +
                " \"${userInfo.firstName}\", \"${userInfo.lastName}\", \"${userInfo.numberPhone}\"," +
                " \"${userInfo.profilePhoto}\", \"${userInfo.type}\" );"


    ydbConnection.retryCtx.supplyResult { session: Session ->
        session.executeDataQuery(
            query,
            txControl
        )
    }
        .join().getValue()


    query = "UPSERT INTO `user/user_auth`\n" +
            "    ( `user_id`, `login`, `password` )\n" +
            "VALUES (\"${userAuth.userId}\", \"${userAuth.login}\",\"${userAuth.password}\");"

    ydbConnection.retryCtx.supplyResult { session: Session ->
        session.executeDataQuery(
            query,
            txControl
        )
    }
        .join().value
}
