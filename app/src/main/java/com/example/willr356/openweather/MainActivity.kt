package com.example.willr356.openweather

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class MainActivity: AppCompatActivity() {

  private var weatherTextView: TextView? = null
  private var weatherImageView: ImageView? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    weatherImageView = findViewById<ImageView>(R.id.weather_image_view)
    weatherTextView = findViewById<TextView>(R.id.city_text_view)
    WeatherTask().execute()
  }

  private class WeatherTask: AsyncTask<Unit, Unit, Unit>() {
    override fun doInBackground(vararg params: Unit?) {
      ApiManager.requestWeather("Pasadena")
    }

    override fun onPostExecute(result: Unit?) {
      super.onPostExecute(result)
    }
  }
}
