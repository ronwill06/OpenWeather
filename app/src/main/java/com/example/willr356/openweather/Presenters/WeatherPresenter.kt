package com.example.willr356.openweather.presenters

import com.example.willr356.openweather.Weather
import com.example.willr356.openweather.api.ApiManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class WeatherPresenter: Weather.Presenter {

  override fun requestWeatherData(cityName: String) {
    ApiManager.requestWeather("Pasadena")
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe()
  }

  override fun bind(view: Weather.View) {

  }
}