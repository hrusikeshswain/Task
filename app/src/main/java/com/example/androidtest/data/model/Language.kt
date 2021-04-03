package com.example.androidtest.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable



@Serializable
data class Language(
    @SerialName("iso639_1")
    val iso6391: String,

    @SerialName("iso639_2")
    val iso6392: String,

    val name: String,
    val nativeName: String
)