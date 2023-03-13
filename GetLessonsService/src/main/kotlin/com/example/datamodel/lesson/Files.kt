package com.example.datamodel.lesson

import kotlinx.serialization.Serializable

@Serializable
data class Files(
    val files: MutableList<String>
)
