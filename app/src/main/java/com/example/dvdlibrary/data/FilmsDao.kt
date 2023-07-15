package com.example.dvdlibrary.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import java.util.concurrent.Flow

@Dao
interface FilmsDao {
    @Insert
    fun insertFilm(film: Film)

    @Query ("SELECT * FROM film")
    fun allFilms(): kotlinx.coroutines.flow.Flow<List<Film>>
}