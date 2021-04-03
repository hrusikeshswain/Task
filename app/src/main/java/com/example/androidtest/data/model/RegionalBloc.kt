package com.example.androidtest.data.model

import kotlinx.serialization.Serializable

@Serializable
data class RegionalBloc (
    val acronym: String,
    val name: String,
    val otherAcronyms: Array<String>,
    val otherNames: Array<String>
)