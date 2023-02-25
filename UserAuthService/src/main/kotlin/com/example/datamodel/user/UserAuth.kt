package com.example.datamodel.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserAuth(

    @SerialName("user_id")
    var userId: String,

    var login: String,

    var password: String
)
