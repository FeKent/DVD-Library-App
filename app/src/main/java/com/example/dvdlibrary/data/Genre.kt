package com.example.dvdlibrary.data

import androidx.annotation.DrawableRes
import com.example.dvdlibrary.R

enum class Genre(val printName: String, @DrawableRes val icon: Int) {
    Action("Action", R.drawable.genre_action),
    Adventure("Adventure", R.drawable.genre_adventure),
    Animated("Animated", R.drawable.genre_animated),
    Christmas("Christmas", R.drawable.genre_christmas),
    Crime("Crime", R.drawable.genre_crime) ,
    Comedy("Comedy", R.drawable.genre_comedy),
    Documentary("Documentary", R.drawable.genre_documentary) ,
    Drama("Drama", R.drawable.genre_drama),
    Family("Family", R.drawable.genre_family),
    Fantasy("Fantasy", R.drawable.genre_fantasy),
    Halloween("Halloween", R.drawable.genre_halloween),
    Historical("Historical", R.drawable.genre_historical),
    Horror("Horror", R.drawable.genre_horror),
    Lgbt("LGBT+", R.drawable.genre_lgbt),
    Musical("Musical", R.drawable.genre_musical),
    Mystery("Mystery", R.drawable.genre_mystery),
    Parody("Parody", R.drawable.genre_parody),
    Romance("Romance", R.drawable.genre_romance),
    SciFi("Sci-Fi", R.drawable.genre_scifi),
    Sports("Sports", R.drawable.genre_sports),
    Thriller("Thriller", R.drawable.genre_thriller),
    Vampire("Vampire", R.drawable.genre_vampire),
    War("War", R.drawable.genre_war),
    Western("Western", R.drawable.genre_western),
    Werewolf("Werewolf", R.drawable.genre_werewolf),
    Zombie("Zombie", R.drawable.genre_zombie),
}