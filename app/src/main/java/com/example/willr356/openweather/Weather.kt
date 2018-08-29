package com.example.willr356.openweather

import org.json.JSONObject

class Weather(jsonObject: JSONObject) {
  var longitude: Int? = null
  var latitude: Int? = null
  var name: String? = null
  var description: String? = null
  var icon: String? = null

  companion object {
    private const val TAG = "Weather"
  }

  init {
    val coordinates = jsonObject.getJSONObject("coord")
    longitude = coordinates.getInt("lon")
    latitude = coordinates.getInt("lat")

    val weather = jsonObject.getJSONArray("weather")
    val weatherObject = weather.getJSONObject(0)
    description = weatherObject.getString("description")
    icon = weatherObject.getString("icon")
  }
}