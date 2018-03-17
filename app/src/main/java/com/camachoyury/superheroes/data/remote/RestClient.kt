package com.camachoyury.superheroes.data.remote

import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by Yury Camacho on 14/02/2018.
 */
class RestClient @Inject constructor(retrofit: Retrofit){

    private val heroesApi by lazy { retrofit.create(HeroApi::class.java) }



}