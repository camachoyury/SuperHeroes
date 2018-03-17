package com.camachoyury.superheroes.presentation.HeroesList

import com.camachoyury.superheroes.data.model.character.CharacterDataWrapper

/**
 * Created by Yury Camacho on 14/02/2018.
 */
interface HeroesView {

    fun displayHeroes(characterDataWrapper: CharacterDataWrapper)
    fun displayNoHeroes()
    fun displayLoading()
    fun displayError()
    fun hideLoading()
}