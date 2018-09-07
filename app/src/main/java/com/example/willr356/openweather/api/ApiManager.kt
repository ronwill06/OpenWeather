package com.example.willr356.openweather.api

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.willr356.openweather.model.WeatherModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.net.URL

object ApiManager {
   private const val TAG = "ApiManager"
   private const val API_KEY = "19f3120e16de2d5fa436c6b17b4a388f"
   private const val weatherUrl = "https://api.openweathermap.org/data/2.5/weather"
   private const val imageUrl = "https://openweathermap.org/img/w/"

  fun requestWeather(city: String): Single<WeatherModel> {
    val url = URL("$weatherUrl?q=${city}&APPID=$API_KEY")
    val httpClient = OkHttpClient()
    val request = Request.Builder()
        .url(url)
        .build()

    //Needs to be called on background thread
    val response = httpClient.newCall(request).execute()

    if (response.code() != 200) {
      return Single.error(Throwable("Network Exception"))
    }

    val json = response.body()?.string()
    val jsonObject = JSONObject(json)
    val weather = WeatherModel(jsonObject)

    return Single.just(weather)
  }

  fun requestImage(iconString: String): Single<Bitmap?> {
    val url = URL("$imageUrl$iconString.png")
    val httpClient = OkHttpClient()
    val request = Request.Builder()
        .url(url)
        .build()

    val response = httpClient.newCall(request).execute()

    if (response.code() != 200) {
      return Single.error(Throwable("Network Exception"))
    }

    val bytes = response.body()?.byteStream()
    val bitmap = BitmapFactory.decodeStream(bytes)
    return Single.just(bitmap)
  }
}