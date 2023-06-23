package com.example.dvdlibrary.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.example.dvdlibrary.model.Film

@Composable
fun IntroScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IntroTextField(modifier = Modifier.padding(top = 16.dp))
        Spacer(modifier = Modifier.padding(24.dp))
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            FilmRow(
                film = Film(
                    0,
                    "Night of the Day of the Dawn of the Son of the Bride of the Return of the Revenge of the Terror of the Attack of the Evil, Mutant, Hellbound, Flesh-Eating Subhumanoid Zombified Living Dead, Part 2: In Shocking 2-D",
                    0,
                    "Pongo",
                    0,
                    0,
                    0
                )
            )
            FilmRow(film = Film(0, "Stu", 0, "Pongo", 0, 0, 0))
            FilmRow(film = Film(0, "Stu", 0, "Pongo", 0, 0, 0))
            FilmRow(film = Film(0, "Stu", 0, "Pongo", 0, 0, 0))
            FilmRow(film = Film(0, "Stu", 0, "Pongo", 0, 0, 0))
            FilmRow(film = Film(0, "Snippy", 0, "Pongo", 0, 0, 0))
        }
        Spacer(modifier = Modifier.padding(32.dp))
        FloatingActionButton(onClick = { /*TODO*/ }, content = ({
            Icon(
                painter = painterResource(R.drawable.ic_add),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground
            )
        }))
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
            .clickable {  }
    ) {
        Row() {
            Image(
                painter = painterResource(id = R.drawable.genre_zombie),
                contentDescription = null,
                modifier = Modifier.align(alignment = CenterVertically)
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