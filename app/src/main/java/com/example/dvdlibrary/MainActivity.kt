package com.example.dvdlibrary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.dvdlibrary.model.Film
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
fun DvdApp(modifier: Modifier = Modifier) {
    FilmCardPreview()
}

@Composable
fun FilmCard(film: Film, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = stringResource(film.date))
                Text(text = stringResource(film.title))
            }
            Image(
                modifier = modifier.fillMaxWidth(),
                painter = painterResource(film.poster),
                contentDescription = stringResource(film.description),
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = stringResource(film.year))
                Text(text = stringResource(film.director))
            }
            Text(text = stringResource(film.genre))
        }
    }
}

@Preview
@Composable
private fun FilmCardPreview() {
    FilmCard(
        Film(
            R.string.date_1,
            R.string.title_1,
            R.drawable.poster_1,
            R.string.description_1,
            R.string.year_1,
            R.string.director_1,
            R.string.genre_1
        )
    )
}