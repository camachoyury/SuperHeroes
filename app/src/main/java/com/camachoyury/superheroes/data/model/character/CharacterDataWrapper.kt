package com.camachoyury.superheroes.data.model.character

/**
 * Created by Yury Camacho on 14/02/2018.
 */

data class CharacterDataWrapper(
        val code: Int, val status: String ,
        val copyright: String, val  attributionText: String,
        val attributionHTML: String, val etag: String , val data: Data )