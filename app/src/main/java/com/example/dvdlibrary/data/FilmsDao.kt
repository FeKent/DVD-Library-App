package com.example.dvdlibrary.data

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface FilmsDao {
    @Insert
    suspend fun insertFilm(film: Film)

    @Delete
    suspend fun delete(film: Film)

    @Update
    suspend fun editFilm(film: Film)

    @Query ("SELECT * FROM film")
    fun allFilms(): Flow<List<Film>>

    @Query ("SELECT * FROM film WHERE film.id = :filmId LIMIT 1")
    suspend fun getFilm(filmId: Int): Film
//  Sorted By Queries
//  Title
    @Query("""
        SELECT * FROM film
        ORDER BY
            CASE
                WHEN title LIKE 'The %' THEN SUBSTR(title, 5)
                ELSE title
            END ASC
    """)
    fun filmsByTitleAsc(): PagingSource<Int, Film>

    @Query("""
        SELECT * FROM film
        ORDER BY
            CASE
                WHEN title LIKE 'The %' THEN SUBSTR(title, 5)
                ELSE title
            END DESC
    """)
    fun filmsByTitleDesc(): PagingSource<Int, Film>

//  Genre
    @Query("""
        SELECT * FROM film
        ORDER BY genre1 ASC, genre2 ASC
    """)
    fun filmsByGenreAsc(): PagingSource<Int, Film>

    @Query("""
        SELECT * FROM film
        ORDER BY genre1 DESC, genre2 DESC
    """)
    fun filmsByGenreDesc(): PagingSource<Int, Film>

//  Year
    @Query("""
        SELECT * FROM film ORDER BY year ASC
    """)
    fun filmsByYearAsc(): PagingSource<Int, Film>

    @Query("""
        SELECT * FROM film ORDER BY year DESC
    """)
    fun filmsByYearDesc(): PagingSource<Int, Film>

    // Runtime
    @Query("""
        SELECT * FROM film ORDER BY runtime ASC
    """)
    fun filmsByRuntimeAsc(): PagingSource<Int, Film>

    @Query("""
        SELECT * FROM film ORDER BY runtime DESC
    """)
    fun filmsByRuntimeDesc(): PagingSource<Int, Film>

    // ID
    @Query("""
        SELECT * FROM film ORDER BY id ASC
    """)
    fun filmsByIdAsc(): PagingSource<Int, Film>

    @Query("""
        SELECT * FROM film ORDER BY id DESC
    """)
    fun filmsByIdDesc(): PagingSource<Int, Film>
}