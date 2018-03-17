package com.camachoyury.superheroes.data.remote

import com.camachoyury.superheroes.data.model.character.CharacterDataWrapper
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Yury Camacho on 14/02/2018.
 */
interface HeroApi {

    @GET("characters")
    fun searchCharacters(@Query("name") name: String): Observable<CharacterDataWrapper>
}