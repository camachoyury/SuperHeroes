package com.camachoyury.superheroes.data.repository

import com.camachoyury.superheroes.data.model.character.CharacterDataWrapper
import com.camachoyury.superheroes.data.model.character.Data
import com.camachoyury.superheroes.data.remote.HeroApi
import io.reactivex.Observable
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by Yury Camacho on 14/02/2018.
 */
class HeroesRepositoryImpl<T> @Inject constructor (val retrofit: Retrofit): HeroesRepository {



    override fun searchCharacter(name: String): Observable<CharacterDataWrapper> {

        var heroService = retrofit.create(HeroApi::class.java)

        return heroService.searchCharacters(name)

    }

//     fun searchCharacterData(name: String): Observable<Data>? {
//
//        var heroService = retrofit.create(HeroApi::class.java)
//
//        return heroService.searchCharacters(name).
//                flatMap { x -> (x.data) }
//

}