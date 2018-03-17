package com.camachoyury.superheroes.data.model.character

import android.R.attr.thumbnail



/**
 * Created by Yury Camacho on 14/02/2018.
 */
data class Result(val id: Long, val name: String, val description: String, val modified: String, val thumbnail: Thumbnail, val resourceURI: String, val comics: Comics, val series: Series, val stories: Stories, val events: Events, val urls: List<Url>)

