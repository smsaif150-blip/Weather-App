package com.example.weatherapp.repository

import com.example.weatherapp.model.MyData
import com.example.weatherapp.utils.retrofitInstance
import java.io.IOException

class WeatherRepository {

    val key = "af3db7f17b1644f0aa444546262005 "

   suspend fun getWeather(city: String): MyData?{
        return try {
            val response = retrofitInstance.api.getWeather(key,city)
            if (response.isSuccessful)
            {
                response.body()
            }
            else {
                throw Exception("City not found")
            }
        }catch (e: IOException)
        {
            throw Exception("No Internet Connection")
        }
       catch (e: Exception)
       {
           throw Exception(e.message?: "Something went wrong")
       }
    }

}