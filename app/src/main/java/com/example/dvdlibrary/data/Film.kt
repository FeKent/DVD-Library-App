package com.example.dvdlibrary.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Film(
   @PrimaryKey (autoGenerate = true) val id: Int = 0,
   @StringRes val runtime: Int,
   val title: String,
   val posterPath: String,
   val description: String,
   @StringRes val year: Int,
   val director: String,
   val genre1: Genre,
   val genre2: Genre? = null,
)
