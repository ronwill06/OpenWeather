package com.example.willr356.openweather.api

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.example.willr356.openweather.WeatherRepository
import com.example.willr356.openweather.model.WeatherModel
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.HttpURLConnection.HTTP_OK
import java.net.MalformedURLException
import java.net.URL
import java.util.jar.JarOutputStream

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

    val response = httpClient.newCall(request).execute()

    if (response.code() != 200) {
      return Single.error(Throwable("Network Exception"))
    }

    val json = response.body()?.string()
    val jsonObject = JSONObject(json)
    val weather = WeatherModel(jsonObject)

    return Single.just(weather)
  }

  fun requestImage(iconString: String): Bitmap? {
    val url = URL("$imageUrl$iconString.png")
    val connection = url.openConnection() as HttpURLConnection

    try {
      connection.connect()

      if (connection.responseCode == HTTP_OK) {
        val inputStream = connection.inputStream
        return BitmapFactory.decodeStream(inputStream)
      }
    } catch (ioe: IOException) {

    } finally {
      connection.inputStream.close()
      connection.disconnect()
    }
    return null
  }
}