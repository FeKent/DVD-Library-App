package com.example.dvdlibrary.data

import com.example.dvdlibrary.R
import com.example.dvdlibrary.model.Film

class Datasource() {
    fun loadFilms(): List<Film> {
        return listOf<Film>(
            Film(
                R.string.date_1,
                R.string.title_1,
                R.drawable.poster_1,
                R.string.description_1,
                R.string.year_1,
                R.string.director_1,
                R.string.genre_1
            ),
        )
    }
}