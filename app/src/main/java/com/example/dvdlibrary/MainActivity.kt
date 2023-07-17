package com.example.dvdlibrary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.room.Room
import com.example.dvdlibrary.Screen.Add
import com.example.dvdlibrary.Screen.Details
import com.example.dvdlibrary.Screen.Intro
import com.example.dvdlibrary.composables.AddScreen
import com.example.dvdlibrary.composables.FilmScreen
import com.example.dvdlibrary.composables.IntroScreen
import com.example.dvdlibrary.composables.MainViewModel
import com.example.dvdlibrary.data.DvdAppDatabase
import com.example.dvdlibrary.data.Film
import com.example.dvdlibrary.ui.theme.DVDLibraryTheme
import kotlinx.coroutines.launch


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
    val database by remember {
        mutableStateOf(
            Room.databaseBuilder(
                appContext,
                DvdAppDatabase::class.java,
                "database-name"
            ).build()
        )
    }
    val coroutineScope = rememberCoroutineScope()
    val films by database.filmsDao().allFilms().collectAsStateWithLifecycle(emptyList())


    when (val cs = currentScreen) {
        Intro -> IntroScreen(
            films = films.sortedBy(Film::title),
            onAddBtnTap = { currentScreen = Add },
            onFilmTap = { film -> currentScreen = Details(film) },
            removeFilm = {coroutineScope.launch{database.filmsDao().delete(it)}}
        )

        Add -> AddScreen(onFilmEntered = {
            coroutineScope.launch {
                database.filmsDao().insertFilm(it)
                currentScreen = Intro
            }
        })

        is Details -> FilmScreen(cs.film, onReturnTap = { currentScreen = Intro })
    }
}


sealed class Screen {
    object Intro : Screen()
    object Add : Screen()
    class Details(val film: Film) : Screen()
}

