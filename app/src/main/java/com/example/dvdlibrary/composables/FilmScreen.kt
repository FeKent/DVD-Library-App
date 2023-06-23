package com.example.dvdlibrary.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dvdlibrary.R
import com.example.dvdlibrary.model.Film

@Composable
fun FilmScreen(film: Film, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = film.title,
            fontSize = 28.sp,
            fontWeight = FontWeight.SemiBold,
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Added:", fontSize = 13.sp, fontStyle = FontStyle.Italic)
            Text(text = stringResource(film.date), fontWeight = FontWeight.SemiBold)
        }
        Image(
            modifier = modifier
                .fillMaxWidth()
                .height(600.dp)
                .width(300.dp),
            painter = painterResource(film.poster),
            contentDescription = film.description,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Year: ", fontSize = 13.sp, fontStyle = FontStyle.Italic)
                Text(text = stringResource(film.year), fontWeight = FontWeight.SemiBold)
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Director: ", fontSize = 13.sp, fontStyle = FontStyle.Italic)
                Text(text = stringResource(film.director), fontWeight = FontWeight.SemiBold)
            }

        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Genre: ", fontSize = 13.sp, fontStyle = FontStyle.Italic)
            Text(
                text = stringResource(film.genre),
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FilmScreenPreview() {
    FilmScreen(
        Film(
            R.string.date_1,
            "Resident Evil",
            R.drawable.poster_1,
            "A movie poster of two women standing back to back holding guns",
            R.string.year_1,
            R.string.director_1,
            R.string.genre_1
        )
    )
}
