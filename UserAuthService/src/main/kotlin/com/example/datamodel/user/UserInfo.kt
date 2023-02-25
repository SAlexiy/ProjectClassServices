package com.example.datamodel.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(

    @SerialName("user_id")
    var userId: String,

    var birthday: String = "2000-01-01",

    var email: String = "",

    @SerialName("first_name")
    var firstName: String = "YourName",

    @SerialName("last_name")
    var lastName: String = "YourLastName",

    @SerialName("number_phone")
    var numberPhone: String = "",

    @SerialName("profile_photo")
    var profilePhoto: String = "",

    var type: String
)
