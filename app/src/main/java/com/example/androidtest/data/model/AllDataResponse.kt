package com.example.androidtest.data.model

import kotlinx.serialization.Serializable

@Serializable
data class AllDataResponse(
    val name: String,
    val topLevelDomain: List<String>,
    val alpha2Code: String,
    val alpha3Code: String,
    val callingCodes: List<String>,
    val capital: String,
    val altSpellings: List<String>,
    val region: String,
    val subregion: String,
    val population: Double,
    val latlng: List<Double>,
    val demonym: String,
    val area: Double,
    val gini: Double,
    val timezones: List<String>,
    val borders: List<String>,
    val nativeName: String,
    val numericCode: String,
    val currencies: List<Currency>,
    val languages: List<Language>,
    val translations: Translations,
    val flag: String,
    val regionalBlocs: List<RegionalBloc>,
    val cioc: String
)