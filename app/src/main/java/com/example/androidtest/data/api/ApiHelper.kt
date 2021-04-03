package com.example.androidtest.data.api




class ApiHelper(private val apiService: ApiInterface) {

    suspend fun getHomeData() = apiService.getHomeData()

}