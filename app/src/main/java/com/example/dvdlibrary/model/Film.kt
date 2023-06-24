package com.example.dvdlibrary.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.dvdlibrary.Genre

data class Film(
   @StringRes val runtime: Int,
   val title: String,
   @DrawableRes val poster: Int,
   val description: String,
   @StringRes val year: Int,
   @StringRes val director: Int,
   val genre1: Genre,
   val genre2: Genre? = null,
)
