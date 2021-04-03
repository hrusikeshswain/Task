package com.example.androidtest.data.api


import com.example.androidtest.data.model.AllDataResponse


import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by Hrusikesh swain on 6/6/2020.
 * Be U Salons
 * hrusikeshswain@beusalons.com
 */
interface ApiInterface {

    @GET("/rest/v2/all")
    suspend fun getHomeData(): ArrayList<AllDataResponse>


}