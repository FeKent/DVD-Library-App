package com.example.dvdlibrary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.room.Room
import com.example.dvdlibrary.Screen.*
import com.example.dvdlibrary.composables.AddScreen
import com.example.dvdlibrary.composables.FilmScreen
import com.example.dvdlibrary.composables.IntroScreen
import com.example.dvdlibrary.data.DvdAppDatabase
import com.example.dvdlibrary.data.Genre
import com.example.dvdlibrary.data.Film
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
    var currentScreen by remember { mutableStateOf<Screen>(Intro) }

    val appContext = LocalContext.current.applicationContext
    val database by remember { mutableStateOf(
        Room.databaseBuilder(
        appContext,
        DvdAppDatabase::class.java,
        "database-name"
    ).build()) }


    var films by remember { mutableStateOf(listOf(
        Film(0, 0, "Scary Movie", 0, "Pongo", 0, "", Genre.Parody),
        Film(0, R.string.run_1, "Resident Evil", R.drawable.poster_1, "Resident Evil", R.string.year_1, "Paul W. S. Anderson", Genre.Zombie),
        Film(0, 0, "Lord of the Rings", 0, "Pongo", 0, "", Genre.Fantasy),
        Film(0, 0, "A League of Their Own", 0, "Pongo", 0, "", Genre.Sports),
        Film(0, 0, "The Good, The Bad, And The Ugly", 0, "Pongo", 0, "", Genre.Western),
        Film(0, 0, "The Exorcist", 0, "Pongo", 0, "", Genre.Horror),
        Film(0, 0, "Die Hard", 0, "Pongo", 0, "", Genre.Action),
        Film(0, 0, "Pirates Of The Caribbean", 0, "Pongo", 0, "", Genre.Adventure),
        Film(0, 0, "White Chicks", 0, "Pongo", 0, "", Genre.Comedy),
        Film(0, 0, "The Godfather", 0, "Pongo", 0, "", Genre.Drama),
        Film(0, 0, "Repo: The Genetic Opera", 0, "Pongo", 0, "", Genre.Musical),
        Film(0, 0, "Knives Out", 0, "Pongo", 0, "", Genre.Mystery),
        Film(0, 0, "The Notebook", 0, "Pongo", 0, "", Genre.Romance),
        Film(0, 0, "The Silence Of The Lambs", 0, "Pongo", 0, "", Genre.Thriller),
        Film(0, 0, "Star Wars", 0, "Pongo", 0, "", Genre.SciFi),
        Film(0, 0, "D.E.B.S", 0, "Pongo", 0, "", Genre.Lgbt),
    )) }


    when (val cs = currentScreen){
       Intro -> IntroScreen(films = films.sortedBy(Film::title), onAddBtnTap = {currentScreen = Add}, onFilmTap = { film -> currentScreen = Details(film)})
       Add -> AddScreen(onFilmEntered = {
           films = films+it
           currentScreen = Intro
       })
       is Details -> FilmScreen(cs.film, onReturnTap = {currentScreen = Intro})
   }
}


sealed class Screen{
    object Intro : Screen ()
    object Add : Screen ()
    class Details(val film: Film) : Screen()
}

