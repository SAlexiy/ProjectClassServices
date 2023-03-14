package com.example.datamodel.lesson

import kotlinx.serialization.Serializable

@Serializable
data class Files(
    val files: MutableList<String>
){
    fun toJsonString(): String {
        var str: String = ""

        for (i in 0 until  files.size){
            str += "\"${files[i]}\""

            if (i != files.size - 1) str += ","

        }

        return "[$str]"
    }
}
