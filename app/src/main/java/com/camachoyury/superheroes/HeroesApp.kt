package com.camachoyury.superheroes

import android.app.Application
import com.camachoyury.superheroes.presentation.di.AppComponent
import com.camachoyury.superheroes.presentation.di.AppModule
import com.camachoyury.superheroes.presentation.di.DaggerAppComponent


/**
 * Created by Yury Camacho on 15/02/2018.
 */
class HeroesApp : Application() {

     lateinit var appComponent: AppComponent

    private fun initDagger(application: HeroesApp): AppComponent = DaggerAppComponent.builder().appModule(AppModule(application)).build()

    override fun onCreate() {
        super.onCreate()

        appComponent = initDagger(this)
    }
}
