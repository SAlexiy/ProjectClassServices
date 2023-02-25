package com.example.userauthservice.datasource

import com.example.datamodel.user.UserAuth
import com.example.datamodel.user.UserInfo
import com.example.userauthservice.datasource.user_data.UserData
import com.example.userauthservice.datasource.user_data.addUserData
import com.example.userauthservice.datasource.user_data.getUserAuthData
import com.example.userauthservice.datasource.user_data.isLoginFree
import java.util.logging.Logger

class UserDataRepository(
    private val ydbConnection: YDBConnection
) : UserData {

    val log = Logger.getLogger("UserDataRepository")

    /**добавляет пользвателя*/
    override fun addUser(userInfo: UserInfo, userAuth: UserAuth) {
        log.info("addUser: ${userInfo.toString()} \n ${userAuth.toString()}")

        addUserData(userInfo, userAuth, ydbConnection)
    }

    /**получает UserAuth*/
    override fun getUserPassword(login: String): String {
        log.info("getUserAuth: $login")

        return getUserAuthData(login, ydbConnection)
    }

    override fun checkLoginFree(login: String): Boolean {
        return isLoginFree(login, ydbConnection)
    }

}
