package com.example.willr356.openweather

import android.graphics.Bitmap
import android.media.Image
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

  private lateinit var weatherTextView: TextView
  private lateinit var weatherImageView: ImageView

  private var weather: Weather? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    weatherImageView = findViewById<ImageView>(R.id.weather_image_view)
    weatherTextView = findViewById<TextView>(R.id.city_text_view)

    weather = WeatherTask().execute("Pasadena").get()
    val image = ImageDownloadTask().execute(weather?.icon).get()

    setWeatherImage(image)
    setCityName(weather?.name.orEmpty())
  }

  fun setWeatherImage(image: Bitmap) {
    weatherImageView.setImageBitmap(image)
  }

  fun setCityName(city: String) {
    weatherTextView.text = city
  }


  private class WeatherTask: AsyncTask<String, Unit, Weather>() {
    override fun doInBackground(vararg params: String?): Weather? {
      val city = params.first().orEmpty()
      return ApiManager.requestWeather(city)
    }

    override fun onPostExecute(result: Weather?) {
      super.onPostExecute(result)
    }
  }

  private class ImageDownloadTask: AsyncTask<String, Void, Bitmap>() {
    override fun doInBackground(vararg params: String?): Bitmap? {
      val icon = params.first().orEmpty()
      return ApiManager.requestImage(icon)
    }

    override fun onPostExecute(result: Bitmap?) {
      super.onPostExecute(result)
    }
  }
}
