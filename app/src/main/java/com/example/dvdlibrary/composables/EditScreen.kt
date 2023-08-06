package com.example.dvdlibrary.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dvdlibrary.data.Genre

@Composable
fun EditScreen(
    filmName: String,
    modifier: Modifier = Modifier
) {
    var title by remember { mutableStateOf("") }
    var runTime by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }
    var director by remember { mutableStateOf("") }
    var genre by remember { mutableStateOf(Genre.Action) }
    var genre2: Genre? by remember { mutableStateOf(null) }

    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.padding(8.dp))
            Text(text = "Edit '${filmName}' Details", fontSize = 28.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.padding(32.dp))
            AddTextField(label = "Title", value = title, onValueChange = { title = it })
            AddNumField(label = "Runtime", value = runTime, onValueChange = { runTime = it })
            AddNumField(label = "Year", value = year, onValueChange = { year = it })
            AddTextField(label = "Director", value = director, onValueChange = { director = it })
            AddGenreField(label = "Genre", selectedItem = genre, onGenreSelected = { genre = it })
            AddGenreField(
                label = "Optional Genre",
                selectedItem = genre2,
                onGenreSelected = { genre2 = it })
        }
    }
}