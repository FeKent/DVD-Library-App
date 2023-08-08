package com.example.dvdlibrary.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.dvdlibrary.R
import com.example.dvdlibrary.data.Film
import com.example.dvdlibrary.data.Genre


@Composable
fun FilmScreen(film: Film, onReturnTap: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(vertical = 4.dp)
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = film.title,
            fontSize = 28.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = modifier.clickable { onReturnTap() },
            textAlign = TextAlign.Center
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Runtime:", fontSize = 13.sp, fontStyle = FontStyle.Italic)
            Text(text = "${film.runtime} min", fontWeight = FontWeight.SemiBold)
        }

        if (film.poster_path.isNotEmpty()) {
            var cardFace by remember { mutableStateOf(CardFace.Front) }

            FlipCard(
                cardFace = cardFace,
                onClick = { cardFace = cardFace.next },
                modifier = Modifier
                    .fillMaxWidth(),
                front = {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/original${film.poster_path}",
                        contentDescription = film.description,
                        contentScale = ContentScale.Crop,
                        modifier = modifier
                            .fillMaxWidth()
                            .height(600.dp)

                    )
                },
                back = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.secondary),
                        contentAlignment = Alignment.Center,
                    ) {
                        if (film.overview.isNotEmpty()) {
                            Text(
                                text = film.overview,
                                fontSize = 24.sp,
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = modifier
                                    .fillMaxWidth()
                                    .height(600.dp)
                                    .wrapContentHeight(Alignment.CenterVertically)
                                    .padding(horizontal = 56.dp, vertical = 20.dp)
                                    .verticalScroll(rememberScrollState())
                            )
                        }
                    }
                })

        } else {
            if (film.overview.isNotEmpty()) {
                Text(
                    text = film.overview,
                    fontSize = 24.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = modifier
                        .fillMaxWidth()
                        .height(600.dp)
                        .wrapContentHeight(Alignment.CenterVertically)
                        .width(300.dp)
                )
            } else {
                Text(
                    text = "Poster & Overview unavailable",
                    fontSize = 13.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = modifier
                        .fillMaxWidth()
                        .height(600.dp)
                        .wrapContentHeight(Alignment.CenterVertically)
                        .width(300.dp)
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Year: ", fontSize = 13.sp, fontStyle = FontStyle.Italic)
                Text(text = film.year.toString(), fontWeight = FontWeight.SemiBold)
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Genre: ", fontSize = 13.sp, fontStyle = FontStyle.Italic)
                Text(
                    text = listOfNotNull(
                        film.genre1,
                        film.genre2
                    ).joinToString(separator = ", ") { it.printName },
                    fontWeight = FontWeight.SemiBold
                )
            }

        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Starring: ", fontSize = 13.sp, fontStyle = FontStyle.Italic)
            Text(
                text = film.starring,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 2.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FilmScreenPreview() {
    FilmScreen(
        Film(
            0,
            R.string.run_1,
            "Night of the Day of the Dawn of the Son of the Bride of the Return of the Revenge of the Terror of the Attack of the Evil, Mutant, Alien, Flesh Eating, Hellbound, Zombified Living Dead Part 2",
            "",
            "A movie poster of two women standing back to back holding guns",
            "",
            R.string.year_1,
            "Paul W. S. Anderson",
            Genre.Zombie,
            Genre.Action,
        ), onReturnTap = {}
    )
}

