package com.example.dvdlibrary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.dvdlibrary.composables.AddScreen
import com.example.dvdlibrary.composables.FilmScreenPreview
import com.example.dvdlibrary.composables.IntroScreen
import com.example.dvdlibrary.ui.theme.DVDLibraryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DVDLibraryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DvdApp()
                }
            }
        }
    }
}

@Composable
fun DvdApp() {
    IntroScreen()
}


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



