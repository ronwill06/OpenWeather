package com.example.willr356.openweather

import android.graphics.Bitmap
import com.example.willr356.openweather.model.WeatherModel

interface Weather {
  interface View {
     fun updateUI(weather: WeatherModel?)

     fun setImage(bitmap: Bitmap)
  }
  interface Presenter {
    fun requestWeatherData(cityName: String)

    fun requestImage(id: String?)

    fun bind(view: Weather.View)
  }
}
