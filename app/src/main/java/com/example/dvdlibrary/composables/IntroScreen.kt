@file:OptIn(
    ExperimentalFoundationApi::class, ExperimentalFoundationApi::class,
    ExperimentalFoundationApi::class
)

package com.example.dvdlibrary.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.dvdlibrary.R
import com.example.dvdlibrary.data.Film

@Composable
fun IntroScreen(
    films: List<Film>,
    onAddBtnTap: () -> Unit,
    onFilmTap: (Film) -> Unit,
    removeFilm: (Film) -> Unit,
    editFilm: (Film) -> Unit,
    modifier: Modifier = Modifier
) {
    var searchItem by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchTextField(
                searchTerm = searchItem,
                onSearchTermChange = {
                    searchItem = it
                },
                onClearTap = { searchItem = "" },
                modifier = Modifier.padding(top = 16.dp),
            )
            Spacer(modifier = Modifier.height(24.dp))
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                films
                    .filter { film -> film.title.lowercase().contains(searchItem.lowercase()) }
                    .forEach {
                        FilmRow(film = it, onFilmTap = onFilmTap, removeFilm = removeFilm, editFilm = editFilm)
                    }
            }
            Spacer(modifier = Modifier.height(24.dp))
            FloatingActionButton(onClick = { onAddBtnTap() }, content = ({
                Icon(
                    painter = painterResource(R.drawable.ic_add),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }))
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


@Composable
fun SearchTextField(
    searchTerm: String,
    onSearchTermChange: (String) -> Unit,
    onClearTap: () -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current

    TextField(
        value = searchTerm,
        onValueChange = { onSearchTermChange(it) },
        keyboardActions = KeyboardActions(onSearch = { focusManager.clearFocus() }),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        singleLine = true,
        modifier = modifier,
        label = { Text(text = "Film Name", fontStyle = FontStyle.Italic) },
        leadingIcon = { Icon(painter = painterResource(R.drawable.ic_search), "Search Icon") },
        trailingIcon = {
            IconButton(onClick = { onClearTap() }) {
                Icon(painter = painterResource(R.drawable.ic_clear), "Clear Icon")
            }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FilmRow(
    film: Film,
    onFilmTap: (Film) -> Unit,
    removeFilm: (Film) -> Unit,
    editFilm: (Film) -> Unit,
    modifier: Modifier = Modifier
) {
    val showDeleteDialog = remember { mutableStateOf(false) }

    if (showDeleteDialog.value) {
        DeleteAlertDialog(
            onDismiss = { showDeleteDialog.value = false },
            onConfirm = { removeFilm(film); showDeleteDialog.value = false },
            filmName = film.title,
        )
    }

    val showEditDialog = remember { mutableStateOf(false) }

    if (showEditDialog.value) {
        EditAlertDialog(
            onDismiss = { showEditDialog.value = false },
            onConfirm = { editFilm(film); showEditDialog.value = false },
            filmName = film.title
        )
    }

    Box(
        modifier = modifier
            .padding(horizontal = 24.dp)
            .background(color = MaterialTheme.colorScheme.surfaceVariant)
            .fillMaxWidth()
            .combinedClickable(
                onClick = { onFilmTap(film) },
                onDoubleClick = { showDeleteDialog.value = true },
                onLongClick = {showEditDialog.value = true}
            )
    ) {
        Row {
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
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(8.dp)
                    .weight(2f)
            )

            Image(
                painter = painterResource(film.genre2?.icon ?: R.drawable.ic_clearcolor),
                contentDescription = null,
                modifier = Modifier
                    .align(alignment = CenterVertically)
                    .width(24.dp)
            )
            Spacer(modifier = modifier.padding(4.dp))
        }
        Divider(color = MaterialTheme.colorScheme.primary, thickness = 1.dp)
    }
}



