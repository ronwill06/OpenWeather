package com.example.willr356.openweather.ui

import android.graphics.Bitmap
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.willr356.openweather.api.ApiManager
import com.example.willr356.openweather.Weather
import com.example.willr356.openweather.R
import com.example.willr356.openweather.di.OpenWeatherApplication
import com.example.willr356.openweather.model.WeatherModel
import javax.inject.Inject

class MainActivity: AppCompatActivity(), Weather.View {

  companion object {
    private const val TAG = "MainActivity"
  }

  @Inject
  lateinit var presenter: Weather.Presenter

  private lateinit var weatherTextView: TextView
  private lateinit var weatherImageView: ImageView

  private var weather: WeatherModel? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    inject()
    initializeUI()

    presenter.requestWeatherData("Pasadena")
    presenter.bind(this)

    val image = ImageDownloadTask().execute(weather?.icon).get()
    setWeatherImage(image)
    setCityName(weather?.name.orEmpty())
  }

  override fun updateUI() {
  }

  private fun inject() {
    (application as OpenWeatherApplication)
        .applicationComponent
        .inject(this)
  }

  private fun initializeUI() {
    weatherImageView = findViewById(
        R.id.weather_image_view)
    weatherTextView = findViewById(R.id.city_text_view)
  }

  private fun setWeatherImage(image: Bitmap) {
    weatherImageView.setImageBitmap(image)
  }

  fun setCityName(city: String) {
    weatherTextView.text = city
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
