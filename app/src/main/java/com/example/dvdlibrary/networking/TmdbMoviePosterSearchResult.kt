package com.example.dvdlibrary.networking

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.http.GET

data class TmdbMoviePosterSearchResult(
    val poster_path: String?
)





