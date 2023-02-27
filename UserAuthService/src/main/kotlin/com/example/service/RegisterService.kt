package com.example.service

import com.example.datamodel.user.UserAuth
import com.example.datamodel.user.UserInfo
import com.example.userauthservice.datamodel.user.UserReg
import com.example.userauthservice.datasource.UserDataRepository
import java.util.UUID

class RegisterService(private val userDataRepository: UserDataRepository) {

    fun register(userReg: UserReg): Boolean{

        return if (userDataRepository.checkLoginFree(userReg.login)){
            val uuid = UUID.randomUUID().toString()

            userDataRepository.addUser(
                UserInfo(userId = uuid, type = userReg.type),
                UserAuth(userId = uuid, login = userReg.login, password = userReg.password.hashCode().toString())
            )

            true
        } else {
            false
        }

    }
}
