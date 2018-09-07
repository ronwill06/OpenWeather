package com.example.willr356.openweather.di

import com.example.willr356.openweather.presenters.WeatherPresenter
import com.example.willr356.openweather.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
  fun inject(activity: MainActivity)

  fun inject(presenter: WeatherPresenter)
}