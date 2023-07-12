package com.example.dvdlibrary.data

import com.example.dvdlibrary.R

class Datasource() {
    fun loadFilms(): List<Film> {
        return listOf<Film>(
            Film(
                R.string.run_1,
                "Resident Evil",
                R.drawable.poster_1,
                "A movie poster of two women standing back to back holding guns",
                R.string.year_1,
                "Paul W. S. Anderson",
                Genre.Zombie,
                Genre.Action
            ),
        )
    }
}