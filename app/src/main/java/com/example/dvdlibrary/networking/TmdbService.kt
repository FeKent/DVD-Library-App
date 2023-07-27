package com.example.dvdlibrary.networking

import com.example.dvdlibrary.data.Film
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.http.GET

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
    @GET ("search/movie")
    suspend fun getPosters(): TmdbMoviePosterSearchResults
}
