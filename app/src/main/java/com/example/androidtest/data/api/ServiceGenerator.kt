package com.example.androidtest.data.api


import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Hrusikesh swain on 6/6/2020.
 * Be U Salons
 * hrusikeshswain@beusalons.com
 */

class ServiceGenerator{

    companion object {
        var BASE_URL =  "https://restcountries.eu"

        var retrofit: Retrofit? = null

        fun getCurrentData():Retrofit?{
            if (retrofit == null) {
                val client = OkHttpClient.Builder().build()
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.HEADERS
                logging.level = HttpLoggingInterceptor.Level.BODY

                val httpClient = OkHttpClient.Builder()

                httpClient.connectTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .networkInterceptors().add(Interceptor { chain ->
                            val request = chain.request().newBuilder()
                                    .build()
                            chain.proceed(request)
                        })

                // add logging as last intesrceptor
                httpClient.addInterceptor(logging);         //for testing purpose


                val gson = GsonBuilder()
                    .setLenient()
                    .create()
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(httpClient.build())
                    .build()
            }
            return retrofit
        }
    }

}