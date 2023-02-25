package com.example.userauthservice.datamodel.user

import kotlinx.serialization.Serializable

@Serializable
data class UserReg(

    var login: String,

    var password: String,

    var type: String
)
