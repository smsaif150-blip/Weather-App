package com.example.weatherapp.ui

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.viewModels.WeatherViewmodel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var MyViewmodel: WeatherViewmodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MyViewmodel = ViewModelProvider(this).get(WeatherViewmodel::class.java)

        binding.btnSearch.setOnClickListener {
            val city = binding.etEnterCity.text.toString()
            MyViewmodel.getWeather(city)
        }

        MyViewmodel.loadingLivedata.observe(this){
            binding.progressbar.visibility = if (it) View.VISIBLE else View.GONE
        }

        MyViewmodel.errorLiveData.observe(this){
            binding.errorMessage.text = it
            binding.errorMessage.visibility = View.VISIBLE
        }

        MyViewmodel.weather.observe(this){ data ->
            //binding.progressbar.visibility = View.VISIBLE
            if (data!=null)
            {
                binding.city.text = data.location.name
                binding.temperature.text = "${data.current.temp_c.toInt()}°C"
                binding.condition.text = data.current.condition.text
                binding.humidity.text = data.current.humidity.toString()+"%"
                binding.wind.text = data.current.wind_kph.toString()+"km/h"
                binding.feelsLike.text = data.current.feelslike_c.toString()+"°C"
              //  binding.progressbar.visibility = View.INVISIBLE
            }
        }


    }
}