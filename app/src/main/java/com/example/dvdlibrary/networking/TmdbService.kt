package com.example.dvdlibrary.networking

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface TmdbService {
    @GET ("3/search/multi")
    suspend fun getPosters(
        @Header("Authorization") apiKey: String,
        @Query("query") query: String,
        @Query("year") year: String,
    ): TmdbMoviePosterSearchResults
}
