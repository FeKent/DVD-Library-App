package com.example.dvdlibrary.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import java.util.concurrent.Flow

@Dao
interface FilmsDao {
    @Insert
    suspend fun insertFilm(film: Film)

    @Delete
    suspend fun delete(film: Film)

    @Query ("SELECT * FROM film")
    fun allFilms(): kotlinx.coroutines.flow.Flow<List<Film>>
}