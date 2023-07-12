package com.example.dvdlibrary.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FilmsDao {
    @Insert
    fun insertFilm(film: Film)

    @Query ("SELECT * FROM film")
    fun allFilms(): List<Film>
}