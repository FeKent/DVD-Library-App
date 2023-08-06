package com.example.dvdlibrary.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dvdlibrary.R
import com.example.dvdlibrary.data.Film
import com.example.dvdlibrary.data.Genre

@Composable
fun EditScreen(
    filmName: String,
    editDetails: Film,
    navigateBack: () -> Unit,
    onFilmEdited: (Film) -> Unit,
    modifier: Modifier = Modifier
) {
    var title by remember { mutableStateOf(editDetails.title) }
    var runTime by remember { mutableStateOf(editDetails.runtime.toString()) }
    var year by remember { mutableStateOf(editDetails.year.toString()) }
    var director by remember { mutableStateOf(editDetails.director) }
    var genre by remember { mutableStateOf(editDetails.genre1) }
    var genre2: Genre? by remember { mutableStateOf(editDetails.genre2) }

    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = "Edit '${filmName}' Details",
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.padding(32.dp))
            AddTextField(label = "Title", value = title, onValueChange = { title = it })
            AddNumField(label = "Runtime", value = runTime, onValueChange = { runTime = it })
            AddNumField(label = "Year", value = year, onValueChange = { year = it })
            AddTextField(label = "Director", value = director, onValueChange = { director = it })
            AddGenre1Field(label = "Genre", selectedItem = genre, onGenreSelected = {genre = it})
            AddGenre2Field(
                label = "Optional Genre",
                selectedItem = genre2,
                onGenreSelected = { genre2 = it })
            Spacer(modifier = Modifier.padding(24.dp))
            Row {
                BackButton {
                    navigateBack()
                }
                Spacer(modifier = Modifier.padding(horizontal = 16.dp))
                EditFilms(
                    onEditSaveTap = {
                        onFilmEdited(
                            Film(
                                id = editDetails.id,
                                runtime = runTime.toInt(),
                                title = title,
                                poster_path = editDetails.poster_path,
                                description = "",
                                year = year.toInt(),
                                director = director,
                                genre1 = genre,
                                genre2 = genre2,
                            ))
                    }
                )
            }
        }
    }
}

@Composable
fun EditFilms(onEditSaveTap: () -> Unit) {
    FloatingActionButton(onClick = { onEditSaveTap() }, content = ({
        Icon(
            painter = painterResource(R.drawable.ic_add),
            contentDescription = "Save Button",
            tint = MaterialTheme.colorScheme.onBackground
        )
    }))
}