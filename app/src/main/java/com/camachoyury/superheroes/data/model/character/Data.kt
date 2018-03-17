package com.camachoyury.superheroes.data.model.character

import com.google.gson.annotations.SerializedName

/**
 * Created by Yury Camacho on 14/02/2018.
 */

data class Data(val offset: Int, val limit: Int, val total: Int, val count: Int, val results: List<Result>) {
}