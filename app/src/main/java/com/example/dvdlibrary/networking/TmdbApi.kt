package com.example.dvdlibrary.networking

import retrofit2.Retrofit

object TmdbApi {
    val service: TmdbService
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .build()
        service = retrofit.create(TmdbService::class.java)
    }
}