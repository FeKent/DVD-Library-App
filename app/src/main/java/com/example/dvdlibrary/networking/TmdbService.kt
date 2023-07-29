package com.example.dvdlibrary.networking

import com.example.dvdlibrary.data.Film
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

//interface TmdbService{
//    suspend fun request(){
//        val client = OkHttpClient()
//
//        val request = Request.Builder()
//            .url("https://api.themoviedb.org/3/search/movie")
//            .get()
//            .addHeader("accept", "application/json")
//            .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmZjJiN2NiZTdlMzc4NGQwN2U1Y2I3NDUxOTFmODYxZSIsInN1YiI6IjY0YTBhYTU2ODFkYTM5MDE0ZDQ5ZDM0ZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.P_rg4mB-Xp5yXhSp9J_qSkf-9aZ134SIzEZz_HlsQj0")
//            .build()
//
//        val response = client.newCall(request).execute()
//    }
//}

interface TmdbService {
    @GET ("3/search/multi")
    suspend fun getPosters(
        @Header("Authorization") apiKey: String,
        @Query("query") query: String,
        @Query("year") year: String,
    ): TmdbMoviePosterSearchResults
}
