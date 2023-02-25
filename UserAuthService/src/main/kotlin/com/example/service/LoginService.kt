package com.example.userauthservice.service

import com.example.userauthservice.datasource.UserDataRepository

class LoginService(private val userDataRepository: UserDataRepository) {

    fun login(login: String): String{

        return userDataRepository.getUserPassword(login)
    }


}