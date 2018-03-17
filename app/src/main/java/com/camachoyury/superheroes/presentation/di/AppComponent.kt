package com.camachoyury.superheroes.presentation.di

import com.camachoyury.superheroes.presentation.HeroesList.HeroesListActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Yury Camacho on 14/02/2018.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun inject(heroesListActivity: HeroesListActivity)




}