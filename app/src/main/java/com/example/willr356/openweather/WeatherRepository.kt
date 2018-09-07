package com.example.willr356.openweather

import com.example.willr356.openweather.model.WeatherModel

object WeatherRepository {

  private var weather: WeatherModel? = null

  fun saveWeather(currentWeather: WeatherModel) {
    weather = currentWeather
  }

  fun getWeather(): WeatherModel? {
    return weather
  }

  fun getImageCode(): String {
    return weather?.icon.orEmpty()
  }
}