package com.example.dvdlibrary.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.room.Entity
import com.example.dvdlibrary.data.Genre

@Entity
data class Film(
   @StringRes val runtime: Int,
   val title: String,
   @DrawableRes val poster: Int,
   val description: String,
   @StringRes val year: Int,
   val director: String,
   val genre1: Genre,
   val genre2: Genre? = null,
)
