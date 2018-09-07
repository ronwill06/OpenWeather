package com.example.willr356.openweather.presenters

import com.example.willr356.openweather.Weather
import com.example.willr356.openweather.api.ApiManager

class WeatherPresenter: Weather.Presenter {

  override fun requestWeatherData(cityName: String) {
    ApiManager.requestWeather("Pasadena")
  }

  override fun bind(view: Weather.View) {

  }
}