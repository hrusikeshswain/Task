package com.example.androidtest.data.repository

import com.example.androidtest.data.api.ApiHelper
import com.example.androidtest.data.model.AllDataResponse
import com.google.gson.JsonArray


class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getHomeData(): ArrayList<AllDataResponse> {
        return apiHelper.getHomeData()
    }

}