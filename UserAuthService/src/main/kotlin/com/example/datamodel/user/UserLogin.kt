package com.example.datamodel.user

import kotlinx.serialization.Serializable


@Serializable
data class UserLogin(

    var login: String,

    var password: String
)
