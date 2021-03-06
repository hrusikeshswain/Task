package com.example.androidtest.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Translations (
    val de: String,
    val es: String,
    val fr: String,
    val ja: String,
    val it: String,
    val br: String,
    val pt: String,
    val nl: String,
    val hr: String,
    val fa: String
)