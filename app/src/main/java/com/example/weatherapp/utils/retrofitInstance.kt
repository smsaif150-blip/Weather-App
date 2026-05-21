package com.example.weatherapp.utils

import com.example.weatherapp.api.ApiServices
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object retrofitInstance {

    private val BASE_URL = "https://api.weatherapi.com/"

    val api: ApiServices by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiServices::class.java)
    }



}