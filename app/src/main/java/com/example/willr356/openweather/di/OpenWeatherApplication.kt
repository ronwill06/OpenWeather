package com.example.willr356.openweather.di

import android.app.Application

class OpenWeatherApplication: Application() {

  lateinit var applicationComponent: ApplicationComponent

  override fun onCreate() {
    super.onCreate()
    applicationComponent =  DaggerApplicationComponent
        .builder()
        .applicationModule(ApplicationModule(this))
        .build()
  }
}