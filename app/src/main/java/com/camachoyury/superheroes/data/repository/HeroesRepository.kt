package com.camachoyury.superheroes.data.repository

import com.camachoyury.superheroes.data.model.character.CharacterDataWrapper
import io.reactivex.Observable

/**
 * Created by Yury Camacho on 14/02/2018.
 */
interface HeroesRepository {

    fun searchCharacter(name: String):Observable<CharacterDataWrapper>
}