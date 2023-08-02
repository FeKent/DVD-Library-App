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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.dvdlibrary.composables.AddScreen
import com.example.dvdlibrary.composables.FilmScreen
import com.example.dvdlibrary.composables.IntroScreen
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

//@Composable
//fun DvdApp() {
//    var currentScreen by remember { mutableStateOf<Screen>(Intro) }
//    val appContext = LocalContext.current.applicationContext
//    val database by remember {
//        mutableStateOf(
//            Room.databaseBuilder(
//                appContext,
//                DvdAppDatabase::class.java,
//                "database-name"
//            ).build()
//        )
//    }
//    val coroutineScope = rememberCoroutineScope()
//    val films by database.filmsDao().allFilms().collectAsStateWithLifecycle(emptyList())
//    val showDialogState = remember { mutableStateOf(false) }
//
//
//    when (val cs = currentScreen) {
//        Intro -> IntroScreen(
//            films = films.sortedBy(Film::title),
//            onAddBtnTap = { currentScreen = Add },
//            onFilmTap = { film -> currentScreen = Details(film) },
//            removeFilm = { coroutineScope.launch { database.filmsDao().delete(it) } }
//        )
//
//        Add -> AddScreen(onFilmEntered = { newFilm ->
//
//            val isFilmDuplicate = existingFilm(films, newFilm)
//            if (!isFilmDuplicate) {
//                coroutineScope.launch {
//                    database.filmsDao().insertFilm(newFilm)
//                    currentScreen = Intro
//                }
//            } else {
//                showDialogState.value = true
//            }
//        }, backButton = { currentScreen = Intro }, showDialogState = showDialogState
//        )
//
//        is Details -> FilmScreen(cs.film, onReturnTap = { currentScreen = Intro })
//    }
//}
//
//fun existingFilm(filmList: List<Film>, newFilm: Film): Boolean {
//    return filmList.any { existingFilm ->
//        existingFilm.title == newFilm.title && existingFilm.year == newFilm.year
//    }
//}
//
//
sealed class Screen(val route: String) {
    object Intro : Screen("intro")
    object Add : Screen("add")
    class Details(val film: Film) : Screen("details")
}


@Composable
fun DvdApp() {

    var currentScreen by remember { mutableStateOf<Screen>(Screen.Intro) }
    val appContext = LocalContext.current
    val database by remember {
        mutableStateOf(
            Room.databaseBuilder(
                appContext,
                DvdAppDatabase::class.java,
                "database-name"
            ).build()
        )
    }
    val films by database.filmsDao().allFilms().collectAsStateWithLifecycle(emptyList())
    val coroutineScope = rememberCoroutineScope()
    val showDialogState = remember { mutableStateOf(false) }
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Intro.route) {
        composable("intro") {
            IntroScreen(
                films = films.sortedBy(Film::title),
                onAddBtnTap = { currentScreen = Screen.Add },
                onFilmTap = { film -> currentScreen = Screen.Details(film) },
                removeFilm = { coroutineScope.launch { database.filmsDao().delete(it) } }
            )
        }
        composable(Screen.Add.route) {
            AddScreen(onFilmEntered = { newFilm ->

                val isFilmDuplicate = existingFilm(films, newFilm)
                if (!isFilmDuplicate) {
                    coroutineScope.launch {
                        database.filmsDao().insertFilm(newFilm)
                        navController.navigate("intro")
                    }
                } else {
                    showDialogState.value = true
                }
            },
                backButton = { navController.navigate("intro") },
                showDialogState = showDialogState
            )
        }
        composable("details") {
            val film = (currentScreen as? Screen.Details)?.film
            film?.let { FilmScreen(it, onReturnTap = { navController.popBackStack() }) }
        }
    }
}


fun existingFilm(filmList: List<Film>, newFilm: Film): Boolean {
    return filmList.any { existingFilm ->
        existingFilm.title == newFilm.title && existingFilm.year == newFilm.year
    }
}