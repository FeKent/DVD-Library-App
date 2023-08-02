package com.example.dvdlibrary.data


import android.os.Parcelable
import androidx.annotation.StringRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Film(
   @PrimaryKey (autoGenerate = true) val id: Int = 0,
   @StringRes val runtime: Int,
   val title: String,
   val poster_path: String,
   val description: String,
   @StringRes val year: Int,
   val director: String,
   val genre1: Genre,
   val genre2: Genre? = null,
) : Parcelable
