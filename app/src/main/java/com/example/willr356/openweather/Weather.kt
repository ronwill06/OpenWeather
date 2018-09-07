package com.example.willr356.openweather

interface Weather {
  interface View {
     fun updateUI()
  }
  interface Presenter {
    fun requestWeatherData(cityName: String)

    fun bind(view: Weather.View)
  }
}
