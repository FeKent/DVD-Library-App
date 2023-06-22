package com.example.dvdlibrary.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Film(
   @StringRes val date: Int,
   @StringRes val title: Int,
   @DrawableRes val poster: Int,
   @StringRes val description: Int,
   @StringRes val year: Int,
   @StringRes val director: Int,
   @StringRes val genre: Int,
)
