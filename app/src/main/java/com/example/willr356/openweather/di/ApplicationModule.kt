package com.example.willr356.openweather.di

import android.app.Application
import android.content.Context
import com.example.willr356.openweather.Weather
import com.example.willr356.openweather.presenters.WeatherPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

  @Provides
  @Singleton
  fun provideApplicationContext(): Context = application

  @Provides
  @Singleton
  fun provideWeatherPresenter(): Weather.Presenter = WeatherPresenter()
}