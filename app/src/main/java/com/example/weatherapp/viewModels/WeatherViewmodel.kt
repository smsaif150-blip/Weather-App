package com.example.weatherapp.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.model.MyData
import com.example.weatherapp.repository.WeatherRepository
import kotlinx.coroutines.launch

class WeatherViewmodel: ViewModel() {
    val repository = WeatherRepository()

    val weather = MutableLiveData<MyData?>()
    val errorLiveData  = MutableLiveData<String?>()
    val loadingLivedata = MutableLiveData<Boolean>()

    fun getWeather(city: String)
    {
        loadingLivedata.value = true
        viewModelScope.launch {
            try {
                val result = repository.getWeather(city)
                weather.value = result
                errorLiveData.value = null
            }catch (e: Exception)
            {
                errorLiveData.value = e.message
            }
            loadingLivedata.value = false
        }
    }
}