package com.example.dvdlibrary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.room.Room
import com.example.dvdlibrary.composables.AddScreen
import com.example.dvdlibrary.composables.FilmScreen
import com.example.dvdlibrary.composables.IntroScreen
import com.example.dvdlibrary.data.DvdAppDatabase
import com.example.dvdlibrary.data.Film
import com.example.dvdlibrary.ui.theme.DVDLibraryTheme
import kotlinx.coroutines.launch
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay


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
//sealed class Screen {
//    object Intro : Screen
//    object Add : Screen
//    class Details(val film: Film) : Screen
//}


sealed class Screen(val route: String) {
    object Intro : Screen("intro")
    object Add : Screen("add")
    object Details : Screen("details/{filmId}")

}


@Composable
fun DvdApp() {


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
        composable(Screen.Intro.route) {
            IntroScreen(
                films = films.sortedBy(Film::title),
                onAddBtnTap = { navController.navigate(Screen.Add.route) },
                onFilmTap = { film -> navController.navigate("details/${film.id}") },
                removeFilm = { film -> coroutineScope.launch { database.filmsDao().delete(film) } },
                editFilm = {}
            )
        }
        composable(Screen.Add.route) {
            AddScreen(
                onFilmEntered = { newFilm ->
                    val isFilmDuplicate = existingFilm(films, newFilm)
                    if (!isFilmDuplicate) {
                        coroutineScope.launch {
                            database.filmsDao().insertFilm(newFilm)
                            navController.popBackStack()
                        }
                    } else {
                        showDialogState.value = true
                    }
                },
                navigateBack = { navController.popBackStack() },
                showDialogState = showDialogState
            )
        }
        composable(
            Screen.Details.route,
            arguments = listOf(navArgument("filmId") { type = NavType.IntType })
        ) { backStackEntry ->
            val filmId = backStackEntry.arguments?.getInt("filmId")
            if (filmId != null) {
                var film: Film? by remember { mutableStateOf(null) }

                LaunchedEffect(key1 = Unit){
                    delay(2000)
                    // remove delay after loading indicator is added
                    film = database.filmsDao().getFilm(filmId)
                }

                film?.let{
                    FilmScreen(
                        film = it,
                        onReturnTap = { navController.popBackStack() })
                } ?: run{
                    Box(modifier = Modifier.fillMaxSize().background(Color.Yellow)) {
                        // loading indicator
                    }
                }
            }
        }
    }
}


fun existingFilm(filmList: List<Film>, newFilm: Film): Boolean {
    return filmList.any { existingFilm ->
        existingFilm.title == newFilm.title && existingFilm.year == newFilm.year
    }
}