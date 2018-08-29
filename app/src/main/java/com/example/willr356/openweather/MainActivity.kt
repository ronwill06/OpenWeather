package com.example.willr356.openweather

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView

class MainActivity: AppCompatActivity() {

  companion object {
    private const val TAG = "MainActivity"
  }

  private var weatherTextView: TextView? = null
  private var weatherImageView: ImageView? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    weatherImageView = findViewById<ImageView>(R.id.weather_image_view)
    weatherTextView = findViewById<TextView>(R.id.city_text_view)
    WeatherTask().execute("Pasadena")

  }


  private class WeatherTask: AsyncTask<String, Unit, Unit>() {
    override fun doInBackground(vararg params: String?) {
      val array = params
      params.first()?.let { ApiManager.requestWeather(it) }
    }

    override fun onPostExecute(result: Unit?) {
      super.onPostExecute(result)
      val weather = WeatherRepository.getWeather()
      Log.d(TAG, "Weather:${weather?.name}")
    }
  }
}
