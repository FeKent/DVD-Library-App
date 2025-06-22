package com.example.dvdlibrary.networking

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbService {
    @GET("3/search/multi")
    suspend fun getPosters(
        @Header("Authorization") apiKey: String,
        @Query("query") query: String,
        @Query("year") year: String,
    ): TmdbMoviePosterSearchResults

    @GET("3/movie/{movie_id}/credits")
    suspend fun getCast(
        @Header("Authorization") apiKey: String,
        @Path("movie_id") databaseId: Int,
    ): TmdbMovieCastSearchResults

    @GET("3/movie/{movie_id")
    suspend fun getRuntime(
        @Header("Authorization") apiKey: String,
        @Path("movie_id") databaseId: Int,
    ): TmdbMovieRuntimeSearchResult
}
