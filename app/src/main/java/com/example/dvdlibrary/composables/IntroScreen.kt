package com.example.dvdlibrary.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.dvdlibrary.R
import com.example.dvdlibrary.data.Genre
import com.example.dvdlibrary.model.Film

@Composable
fun IntroScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IntroTextField(modifier = Modifier.padding(top = 16.dp))
        Spacer(modifier = Modifier.height(24.dp))
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            FilmRow(film = Film(0, "Scary Movie", 0, "Pongo", 0, 0, Genre.Parody))
            FilmRow(film = Film(R.string.run_1, "Resident Evil", R.drawable.poster_1, "Resident Evil", R.string.year_1, R.string.director_1, Genre.Zombie))
            FilmRow(film = Film(0, "Lord of the Rings", 0, "Pongo", 0, 0, Genre.Fantasy))
            FilmRow(film = Film(0, "A League of Their Own", 0, "Pongo", 0, 0, Genre.Sports))
            FilmRow(film = Film(0, "The Good, The Bad, And The Ugly", 0, "Pongo", 0, 0, Genre.Western))
            FilmRow(film = Film(0, "The Exorcist", 0, "Pongo", 0, 0, Genre.Horror))
            FilmRow(film = Film(0, "Die Hard", 0, "Pongo", 0, 0, Genre.Action))
            FilmRow(film = Film(0, "Pirates Of The Caribbean", 0, "Pongo", 0, 0, Genre.Adventure))
            FilmRow(film = Film(0, "White Chicks", 0, "Pongo", 0, 0, Genre.Comedy))
            FilmRow(film = Film(0, "The Godfather", 0, "Pongo", 0, 0, Genre.Drama))
            FilmRow(film = Film(0, "Repo: The Genetic Opera", 0, "Pongo", 0, 0, Genre.Musical))
            FilmRow(film = Film(0, "Knives Out", 0, "Pongo", 0, 0, Genre.Mystery))
            FilmRow(film = Film(0, "The Notebook", 0, "Pongo", 0, 0, Genre.Romance))
            FilmRow(film = Film(0, "The Silence Of The Lambs", 0, "Pongo", 0, 0, Genre.Thriller))
            FilmRow(film = Film(0, "Star Wars", 0, "Pongo", 0, 0, Genre.SciFi))
            FilmRow(film = Film(0, "D.E.B.S", 0, "Pongo", 0, 0, Genre.Lgbt))
        }
        Spacer(modifier = Modifier.height(24.dp))
        FloatingActionButton(onClick = { /*TODO*/ }, content = ({
            Icon(
                painter = painterResource(R.drawable.ic_add),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground
            )
        }))
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun IntroTextField(modifier: Modifier = Modifier) {

    var searchItem by remember { mutableStateOf("") }

    TextField(
        value = searchItem,
        onValueChange = { searchItem = it },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        singleLine = true,
        modifier = modifier,
        label = { Text(text = "Film Name", fontStyle = FontStyle.Italic) },
        leadingIcon = { Icon(painter = painterResource(R.drawable.ic_search), null) }
    )

}

@Composable
fun FilmRow(film: Film, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(horizontal = 24.dp)
            .background(color = MaterialTheme.colorScheme.surfaceVariant)
            .fillMaxWidth()
            .clickable { }
    ) {
        Row() {
            Spacer(modifier = modifier.padding(4.dp))
            Image(
                painter = painterResource(film.genre1.icon),
                contentDescription = null,
                modifier = Modifier
                    .align(alignment = CenterVertically)
                    .width(24.dp)
            )
            Text(
                text = film.title,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(8.dp)
            )
        }
        Divider(color = MaterialTheme.colorScheme.primary, thickness = 1.dp)
    }

}