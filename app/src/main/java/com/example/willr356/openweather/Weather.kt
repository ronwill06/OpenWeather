package com.example.willr356.openweather

import org.json.JSONObject

class Weather(jsonObject: JSONObject) {
  var longitude: Double? = null
  var latitude: Double? = null
  var name: String? = null
  var description: String? = null
  var icon: String? = null
  var id: String? = null

  companion object {
    private const val TAG = "Weather"
  }

  init {
    val coordinates = jsonObject.getJSONObject("coord")
    name = jsonObject.getString("name").orEmpty()
    longitude = coordinates.getDouble("lon")
    latitude = coordinates.getDouble("lat")

    val weather = jsonObject.getJSONArray("weather")
    val weatherObject = weather.getJSONObject(0)
    description = weatherObject.getString("description").orEmpty()
    icon = weatherObject.getString("icon").orEmpty()
  }
}