package com.example.userauthservice.datasource.user_data

import com.example.datamodel.user.UserAuth
import com.example.datamodel.user.UserInfo

interface UserData {

    fun addUser(userInfo: UserInfo, userAuth: UserAuth)

    fun getUserPassword(login: String) : String

    fun checkLoginFree(login: String): Boolean

}
