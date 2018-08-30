package com.example.willr356.openweather

object WeatherRepository {

  private var weather: Weather? = null

  fun saveWeather(currentWeather: Weather) {
    weather = currentWeather
  }

  fun getWeather(): Weather? {
    return weather
  }

  fun getImageCode(): String {
    return weather?.icon.orEmpty()
  }
}