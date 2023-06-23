package com.example.dvdlibrary.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Film(
   @StringRes val runtime: Int,
   val title: String,
   @DrawableRes val poster: Int,
   val description: String,
   @StringRes val year: Int,
   @StringRes val director: Int,
   @StringRes val genre: Int,
)
