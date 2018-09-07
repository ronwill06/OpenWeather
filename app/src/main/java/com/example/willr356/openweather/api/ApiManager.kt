package com.example.willr356.openweather.api

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.example.willr356.openweather.WeatherRepository
import com.example.willr356.openweather.model.WeatherModel
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.HttpURLConnection.HTTP_OK
import java.net.MalformedURLException
import java.net.URL

object ApiManager {
   private const val TAG = "ApiManager"
   private const val API_KEY = "19f3120e16de2d5fa436c6b17b4a388f"
   private const val weatherUrl = "https://api.openweathermap.org/data/2.5/weather"
   private const val imageUrl = "https://openweathermap.org/img/w/"

  fun requestWeather(city: String): WeatherModel? {
    val url = URL("$weatherUrl?q=${city}&APPID=$API_KEY")
    val httpClient = OkHttpClient()
    val request = Request.Builder()
        .url(url)
        .build()

    val response = httpClient.newCall(request).execute()
    val connection = url.openConnection() as HttpURLConnection
    val sb = StringBuilder()

    try {
      connection.connect()

      if (connection.responseCode == HTTP_OK) {
        val inputStream = connection.inputStream
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))

        var line = bufferedReader.readLine()
        while (line != null) {
          sb.append(line).append("\n")
          line = bufferedReader.readLine()
        }
        inputStream.close()
        bufferedReader.close()

        val jsonObject = JSONObject(sb.toString())
        val weather = WeatherModel(jsonObject)
        WeatherRepository.saveWeather(weather)
        return weather
      }
    } catch (me: MalformedURLException) {
      Log.d(TAG, "Malformed URL")
    } catch (ioe: IOException) {
      Log.d(TAG, "IO Exception ${ioe.localizedMessage}")
    } finally {
      connection.disconnect()
    }

    return null
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