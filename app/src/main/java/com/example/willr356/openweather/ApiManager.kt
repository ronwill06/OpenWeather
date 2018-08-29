package com.example.willr356.openweather

import android.util.Log
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

object ApiManager {
   private const val TAG = "ApiManager"
   private const val API_KEY = "19f3120e16de2d5fa436c6b17b4a388f"
   private const val weatherUrl = "https://api.openweathermap.org/data/2.5/weather"

  fun requestWeather(city: String) {
    val url = URL("$weatherUrl?q=${city}&APPID=$API_KEY")
    val connection = url.openConnection() as HttpURLConnection
    val sb = StringBuilder()

    try {
      connection.connect()

      if (connection.responseCode == HttpURLConnection.HTTP_OK) {
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
        val weather = Weather(jsonObject)
        WeatherRepository.saveWeather(weather)
      }
    } catch (me: MalformedURLException) {
      Log.d(TAG, "Malformed URL")
    } catch (ioe: IOException) {
      Log.d(TAG, "IO Exception ${ioe.localizedMessage}")
    } finally {
      connection.disconnect()
    }
  }
}