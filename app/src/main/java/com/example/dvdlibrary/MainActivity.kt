package com.example.dvdlibrary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.dvdlibrary.composables.AddScreen
import com.example.dvdlibrary.composables.FilmScreen
import com.example.dvdlibrary.composables.IntroScreen
import com.example.dvdlibrary.data.Film
import com.example.dvdlibrary.ui.theme.DVDLibraryTheme
import com.example.dvdlibrary.viewmodels.AppViewModel
import com.example.dvdlibrary.viewmodels.IntroViewModel
import com.example.dvdlibrary.viewmodels.IntroViewModelFactory
import kotlinx.coroutines.flow.emptyFlow
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
sealed class Screen(val route: String) {
    object Intro : Screen("intro")
    object Add : Screen("add")
    object Edit : Screen("edit/{filmId}")
    object Details : Screen("details/{filmId}")

}

@Composable
fun DvdApp() {
    val viewModel: AppViewModel = viewModel()
    val appContext = LocalContext.current
    val films by viewModel.films(appContext).collectAsState(initial = emptyList())

    val coroutineScope = rememberCoroutineScope()
    val showDialogState = remember { mutableStateOf(false) }
    val navController = rememberNavController()



    NavHost(navController = navController, startDestination = Screen.Intro.route) {
        composable(Screen.Intro.route) {
            val introViewModel: IntroViewModel = viewModel(factory = IntroViewModelFactory(
                viewModel.films(appContext)
            ))
            val sortedFilms by introViewModel.sortedFilms.collectAsStateWithLifecycle(initialValue = emptyList())
            val currentSortItemState by introViewModel.currentSortItemState.collectAsStateWithLifecycle(initialValue = 0)
            val sortOrder by introViewModel.sortOrder.collectAsStateWithLifecycle(initialValue = 0)
            val removeFilm: (Film) -> Unit = {film -> coroutineScope.launch{viewModel.deleteFilm(appContext, film)}}

            IntroScreen(
                films = sortedFilms,
                onAddBtnTap = { navController.navigate(Screen.Add.route) },
                onFilmTap = { film -> navController.navigate("details/${film.id}") },
                removeFilm = {film -> removeFilm(film); navController.navigate("intro") },
                editFilm = { film -> navController.navigate("edit/${film.id}") },
                currentSortItem = currentSortItemState,
                updateSortItem = { newItem -> introViewModel.currentSortItemState.value = newItem },
                sortOrder = sortOrder,
                updateSortOrder = {newItem -> introViewModel.sortOrder.value = newItem},
                databaseItemCounter = sortedFilms.size
            )
        }

        composable(Screen.Add.route) {
            AddScreen( filmToEdit = null,
                onFilmEntered = { newFilm ->
                    val isFilmDuplicate = existingFilm(films, newFilm)
                    if (!isFilmDuplicate) {
                        coroutineScope.launch {
                            viewModel.insertFilm(appContext, newFilm)
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
            Screen.Edit.route,
            arguments = listOf(navArgument("filmId") { type = NavType.IntType })
        ) { backStackEntry ->
            val filmId = backStackEntry.arguments?.getInt("filmId")
            if (filmId != null) {
                var film: Film? by remember { mutableStateOf(null) }

                LaunchedEffect(key1 = filmId) {
                    film = viewModel.getFilm(appContext, filmId)
                }

                film?.let { editedFilm ->
                    AddScreen(
                        filmToEdit = editedFilm,
                        onFilmEntered = { updatedFilm ->
                            coroutineScope.launch {
                                viewModel.editFilm(appContext, updatedFilm)
                                navController.popBackStack()
                            }
                        },
                        navigateBack = { navController.popBackStack() },
                        showDialogState = showDialogState
                    )
                }
            }
        }

        composable(
            Screen.Details.route,
            arguments = listOf(navArgument("filmId") { type = NavType.IntType })
        ) { backStackEntry ->
            val filmId = backStackEntry.arguments?.getInt("filmId")
            if (filmId != null) {
                var film: Film? by remember { mutableStateOf(null) }

                LaunchedEffect(key1 = Unit) {
                    film = viewModel.getFilm(appContext, filmId)
                }

                film?.let {
                    FilmScreen(
                        film = it,
                        onReturnTap = { navController.popBackStack() })
                } ?: run {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(), contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(150.dp),
                            strokeWidth = 8.dp
                        )
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