package com.example.dvdlibrary.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dvdlibrary.R
import com.example.dvdlibrary.data.Film
import com.example.dvdlibrary.data.Genre

@Composable
fun FilmScreen(film: Film, onReturnTap: ()-> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
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
        Image(
            modifier = modifier
                .fillMaxWidth()
                .height(600.dp)
                .width(300.dp),
            painter = painterResource(id = R.drawable.generic_poster),
            contentDescription = film.description,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Year: ", fontSize = 13.sp, fontStyle = FontStyle.Italic)
                Text(text = film.year.toString(), fontWeight = FontWeight.SemiBold)
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Director: ", fontSize = 13.sp, fontStyle = FontStyle.Italic)
                Text(text = film.director, fontWeight = FontWeight.SemiBold)
            }

        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Genre: ", fontSize = 13.sp, fontStyle = FontStyle.Italic)
            Text(
                text = film.genre1.printName,
                fontWeight = FontWeight.SemiBold
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
            R.drawable.generic_poster,
            "A movie poster of two women standing back to back holding guns",
            R.string.year_1,
            "Paul W. S. Anderson",
            Genre.Zombie,
            Genre.Action,
        ), onReturnTap = {}
    )
}
