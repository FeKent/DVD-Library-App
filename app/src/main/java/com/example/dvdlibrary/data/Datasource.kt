package com.example.dvdlibrary.data

import com.example.dvdlibrary.R
import com.example.dvdlibrary.model.Film

class Datasource() {
    fun loadFilms(): List<Film> {
        return listOf<Film>(
            Film(
                R.string.run_1,
                "Resident Evil",
                R.drawable.poster_1,
                "A movie poster of two women standing back to back holding guns",
                R.string.year_1,
                R.string.director_1,
                R.string.genre_1
            ),
        )
    }
}