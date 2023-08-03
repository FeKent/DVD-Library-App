package com.example.dvdlibrary.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface FilmsDao {
    @Insert
    suspend fun insertFilm(film: Film)

    @Delete
    suspend fun delete(film: Film)

    @Query ("SELECT * FROM film")
    fun allFilms(): Flow<List<Film>>

    @Query ("SELECT * FROM film WHERE film.id = :filmId LIMIT 1")
    suspend fun getFilm(filmId: Int): Film
}