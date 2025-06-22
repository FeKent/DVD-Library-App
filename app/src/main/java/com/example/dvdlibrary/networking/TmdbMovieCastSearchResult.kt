package com.example.dvdlibrary.networking

data class TmdbMovieCastSearchResult(
    val movie_id: Int,
    val cast: List<String>
)
