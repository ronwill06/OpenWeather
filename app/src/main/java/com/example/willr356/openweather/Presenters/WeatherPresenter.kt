package com.example.willr356.openweather.presenters

import android.util.Log
import com.example.willr356.openweather.Weather
import com.example.willr356.openweather.api.ApiManager
import com.example.willr356.openweather.model.WeatherModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class WeatherPresenter: Weather.Presenter {

  companion object {
    const val TAG = "WeatherPresenter"
  }

  private var weatherModel: WeatherModel? = null
  private var weatherView: Weather.View? = null

  override fun requestWeatherData(cityName: String) {
    ApiManager.requestWeather("Pasadena")
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            { weather ->
              weatherView?.updateUI(weather)
            }, { error ->
          Log.e(TAG, "Error on weather request")
        })
  }

  override fun bind(view: Weather.View) {
    weatherView = view
  }

  override fun requestImage(id: String?) {
    if (id != null) {
      ApiManager.requestImage(id)
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe({ it ->
            it?.let { weatherView?.setImage(it) }
          }, {

          })
    }
  }
}