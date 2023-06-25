package com.example.dvdlibrary.data

import androidx.annotation.DrawableRes
import com.example.dvdlibrary.R

enum class Genre(val printName: String, @DrawableRes val icon: Int) {
    Action("Action", R.drawable.genre_action),
    Adventure("Adventure", R.drawable.genre_adventure),
    Comedy("Comedy", R.drawable.genre_comedy),
    Drama("Drama", R.drawable.genre_drama),
    Fantasy("Fantasy", R.drawable.genre_fantasy),
    Horror("Horror", R.drawable.genre_horror),
    Musical("Musical", R.drawable.genre_musical),
    Mystery("Mystery", R.drawable.genre_mystery),
    Romance("Romance", R.drawable.genre_romance),
    SciFi("Sci-Fi", R.drawable.genre_scifi),
    Sports("Sports", R.drawable.genre_sports),
    Thriller("Thriller", R.drawable.genre_thriller),
    Western("Western", R.drawable.genre_western),
    Parody("Parody", R.drawable.genre_parody),
    Zombie("Zombie", R.drawable.genre_zombie),
    Lgbt("LGBT+", R.drawable.genre_lgbt)
}