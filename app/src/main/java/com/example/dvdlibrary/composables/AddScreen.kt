package com.example.dvdlibrary.composables

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.ExposedDropdownMenuDefaults.TrailingIcon
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dvdlibrary.R
import com.example.dvdlibrary.data.Film
import com.example.dvdlibrary.data.Genre
import com.example.dvdlibrary.networking.TmdbApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import kotlin.math.abs

@Composable
fun AddScreen(
    onFilmEntered: (Film) -> Unit,
    navigateBack: () -> Unit,
    showDialogState: MutableState<Boolean>,
    modifier: Modifier = Modifier,
    filmToEdit: Film? = null,
) {
    val showValidLogState = remember { mutableStateOf(false) }
    val validationLabel = remember { mutableStateOf("") }
    val mContext = LocalContext.current
    val posterScope = CoroutineScope(Dispatchers.Main)
    val apiKey =
        "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmZjJiN2NiZTdlMzc4NGQwN2U1Y2I3NDUxOTFmODYxZSIsInN1YiI6IjY0YTBhYTU2ODFkYTM5MDE0ZDQ5ZDM0ZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.P_rg4mB-Xp5yXhSp9J_qSkf-9aZ134SIzEZz_HlsQj0"

    val isEditMode = filmToEdit != null

    var title by remember { mutableStateOf(filmToEdit?.title ?: "") }
    var runTime by remember { mutableStateOf(filmToEdit?.runtime?.toString() ?: "") }
    var year by remember { mutableStateOf(filmToEdit?.year?.toString() ?: "") }
    var starring by remember { mutableStateOf(filmToEdit?.starring ?: "") }
    var genre by remember { mutableStateOf(filmToEdit?.genre1 ?: Genre.Action) }
    var genre2: Genre? by remember { mutableStateOf(filmToEdit?.genre2) }


    Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 56.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = "${if (isEditMode) "Edit" else "Add"} Film Details",
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.padding(32.dp))
            AddTextField(
                label = "Title",
                value = title,
                onValueChange = { title = it },
                modifier = Modifier.fillMaxWidth()
            )
            AddNumField(
                label = "Runtime",
                value = runTime,
                onValueChange = { runTime = it },
                modifier = Modifier.fillMaxWidth()
            )
            AddNumField(
                label = "Year",
                value = year,
                onValueChange = { year = it },
                modifier = Modifier.fillMaxWidth()
            )
            AddTextField(
                label = "Starring",
                value = starring,
                onValueChange = { starring = it },
                modifier = Modifier.fillMaxWidth()
            )
            AddGenre1Field(
                label = "Genre",
                selectedItem = genre,
                onGenreSelected = { genre = it },
                modifier = Modifier.fillMaxWidth()
            )
            AddGenre2Field(
                label = "Optional Genre",
                selectedItem = genre2,
                onGenreSelected = { genre2 = it },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.padding(24.dp))
            Row {
                BackButton {
                    navigateBack()
                }
                Spacer(modifier = Modifier.padding(horizontal = 16.dp))

                SaveFilm(onSaveTap = {
                    posterScope.launch {
                        try {
                            val response = withContext(coroutineContext) {
                                TmdbApi.service.getPosters(
                                    "Bearer $apiKey", "\"$title\"", year
                                )
                            }


                            val mediaMovies =
                                response.results.filter { it.media_type.equals("movie") }
                            val mediaTv = response.results.filter { it.media_type.equals("tv") }

                            val movies = (mediaMovies + mediaTv)
                                .sortedWith(compareBy
                                { media ->
                                    val resultYearInt = if (media.media_type == "movie") {
                                        media.release_date?.take(4)?.toIntOrNull()
                                    } else {
                                        media.first_airdate?.take(4)?.toIntOrNull()
                                    }
                                        val defaultYear = Int.MAX_VALUE / 2
                                        val distanceFromInputYear =
                                            abs((resultYearInt ?: defaultYear) - year.toInt())
                                        distanceFromInputYear
                                })

                            val posterUrl = if (movies.isNotEmpty()) {
                                val firstMovie = movies[0]
                                firstMovie.poster_path.toString()
                                firstMovie.poster_path ?: ""
                            } else {
                                ""
                            }

                            val getOverview = if (movies.isNotEmpty()) {
                                val beginningMovie = movies[0]
                                beginningMovie.overview.toString()
                                beginningMovie.overview ?: ""
                            } else {
                                ""
                            }

                            val yearIsValid: Boolean = try {
                                val intYear = year.toInt()
                                intYear > 1900 && intYear < LocalDate.now().year + 1
                            } catch (e: Exception) {
                                false
                            }

                            if (runTime.isEmpty()) {
                                validationLabel.value = "Runtime"
                            } else if (title.isEmpty()) {
                                validationLabel.value = "Title"
                            } else if (starring.isEmpty()) {
                                validationLabel.value = "Starring"
                            } else if (!yearIsValid) {
                                validationLabel.value = "Year"
                            }

                            if (runTime.isEmpty() || title.isEmpty() || starring.isEmpty() || !yearIsValid) {
                                showValidLogState.value = true
                                return@launch
                            }
                            val newFilm = Film(
                                id = filmToEdit?.id ?: 0,
                                runTime.toInt(),
                                title,
                                poster_path = posterUrl,
                                overview = getOverview,
                                "",
                                year.toInt(),
                                starring,
                                genre,
                                genre2,
                            )
                            onFilmEntered(newFilm)
                        } catch (e: Exception) {

                            Toast.makeText(
                                mContext,
                                "Error With API; Try Another Film",
                                Toast.LENGTH_SHORT
                            ).show()

                            println("Error occurred while making API request: ${e.printStackTrace()}")
                            println("Error occurred while making API request: ${e.cause} issue one")
                        }
                    }
                })
            }
        }

        if (showValidLogState.value) {
            ValidationDialog(label = validationLabel.value,
                onDismiss = { showValidLogState.value = false })
        }

        if (showDialogState.value) {
            AlertDialog(
                onDismissRequest = { showDialogState.value = false },
                confirmButton = {
                    TextButton(onClick = {
                        showDialogState.value = false
                    }) { Text(text = "OK") }
                },
                text = { Text(text = "This film already exists") },
            )
        }
    }
}


@Composable
fun AddTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        singleLine = true,
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next, capitalization = KeyboardCapitalization.Words
        ),
        modifier = modifier
    )
}

@Composable
fun AddNumField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            capitalization = KeyboardCapitalization.Words,
            keyboardType = KeyboardType.Number
        ),
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddGenre1Field(
    label: String,
    selectedItem: Genre?,
    onGenreSelected: (Genre) -> Unit,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded, onExpandedChange = { expanded = !expanded }, modifier = modifier
    ) {
        TextField(
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            value = selectedItem?.printName ?: "No Genre Selected",
            onValueChange = {},
            readOnly = true,
            label = {
                Text(text = label)
            },
            trailingIcon = {
                TrailingIcon(expanded = expanded)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            Genre.values().forEach { option ->
                DropdownMenuItem({ Text(text = option.printName) }, onClick = {
                    onGenreSelected(option)
                    expanded = false
                })
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddGenre2Field(
    label: String,
    selectedItem: Genre?,
    onGenreSelected: (Genre?) -> Unit,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded, onExpandedChange = { expanded = !expanded }, modifier = modifier
    ) {
        TextField(
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            value = selectedItem?.printName ?: "No Genre Selected",
            onValueChange = {},
            readOnly = true,
            label = {
                Text(text = label)
            },
            trailingIcon = {
                TrailingIcon(expanded = expanded)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem({ Text(text = "No Genre Selected") }, onClick = {
                onGenreSelected(null)
                expanded = false
            })
            Genre.values().forEach { option ->
                DropdownMenuItem({ Text(text = option.printName) }, onClick = {
                    onGenreSelected(option)
                    expanded = false
                })
            }
        }
    }
}


@Composable
fun SaveFilm(onSaveTap: () -> Unit) {
    FloatingActionButton(onClick = { onSaveTap() }, content = ({
        Icon(
            painter = painterResource(R.drawable.ic_add),
            contentDescription = "Save Button",
            tint = MaterialTheme.colorScheme.onBackground
        )
    }))
}

@Composable
fun BackButton(onBackTap: () -> Unit) {
    FloatingActionButton(onClick = { onBackTap() }, content = ({
        Icon(
            painter = painterResource(R.drawable.ic_back),
            contentDescription = "Back Button",
            tint = MaterialTheme.colorScheme.onBackground
        )
    }))
}
