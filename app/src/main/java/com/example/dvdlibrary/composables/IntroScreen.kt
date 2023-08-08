@file:OptIn(
    ExperimentalFoundationApi::class,
    ExperimentalFoundationApi::class,
    ExperimentalFoundationApi::class
)

package com.example.dvdlibrary.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.draw.scale
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
    currentSortItem: Int,
    updateSortItem: (Int) -> Unit,
    sortOrder: Int,
    updateSortOrder: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    var searchItem by remember { mutableStateOf("") }
    val sortItems = arrayOf("Title", "Genre", "Year", "Runtime")
    val filterItems = arrayOf("Title", "Year", "Starring", "Genre")
    var currentFilterItem by remember { mutableStateOf(0) }
    var expandedSort by remember { mutableStateOf(false) }
    var expandedFilter by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Column(
            modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Box {
                    val isCurrentSortOrder: Boolean = 0 == sortOrder
                    val iconRes = if (isCurrentSortOrder) {
                        R.drawable.ic_sort_arrow_up
                    } else {
                        R.drawable.ic_sort_arrow_down
                    }
                    Icon(
                        painter = painterResource(id = iconRes),
                        contentDescription = if (isCurrentSortOrder) "Ascending Order" else "Descending Order",
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .width(50.dp)
                            .scale(1.5f)
                            .clickable { updateSortOrder(if (isCurrentSortOrder) 1 else 0) }
                    )

                    Icon(painter = painterResource(R.drawable.ic_sort),
                        contentDescription = "Sort Button",
                        modifier = Modifier
                            .padding(top = 30.dp)
                            .size(50.dp)
                            .clickable { expandedSort = true })

                    DropdownMenu(
                        expanded = expandedSort,
                        onDismissRequest = { expandedSort = false }) {
                        sortItems.forEachIndexed { itemIndex, itemValue ->
                            val isCurrentSortItem = itemIndex == currentSortItem
                            DropdownMenuItem(
                                text = { Text(text = itemValue) },
                                onClick = {
                                    if (!isCurrentSortItem) {
                                        updateSortItem(itemIndex)
                                    }
                                    expandedSort = false
                                },
                                enabled = !isCurrentSortItem
                            )
                        }
                    }
                }

                SearchTextField(
                    searchTerm = searchItem,
                    onSearchTermChange = {
                        searchItem = it
                    },
                    onClearTap = { searchItem = "" },
                    modifier = Modifier.padding(top = 16.dp),
                    label = "Film ${filterItems[currentFilterItem]}"
                )

                Box {
                    Icon(painter = painterResource(R.drawable.ic_filter),
                        contentDescription = "Filter Button",
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .size(50.dp)
                            .clickable { expandedFilter = true })

                    DropdownMenu(
                        expanded = expandedFilter,
                        onDismissRequest = { expandedFilter = false }) {
                        filterItems.forEachIndexed { itemIndex, itemValue ->
                            DropdownMenuItem(
                                text = { Text(text = itemValue) },
                                onClick = { currentFilterItem = itemIndex; expandedFilter = false },
                                enabled = (itemIndex != currentFilterItem)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                val filmFilters = when (currentFilterItem) {
                    0 -> films.filter { film ->
                        film.title.lowercase().contains(searchItem.lowercase())
                    }

                    1 -> films.filter { film -> film.year.toString() == searchItem }
                    2 -> films.filter { film ->
                        film.starring.lowercase().contains(searchItem.lowercase())
                    }

                    3 -> films.filter { film -> film.genre1.toString() == searchItem || film.genre2?.toString() == searchItem }
                    else -> emptyList()
                }

                filmFilters
                    .forEach {
                        FilmRow(
                            film = it,
                            onFilmTap = onFilmTap,
                            removeFilm = removeFilm,
                            editFilm = editFilm
                        )
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
    label: String,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current

    TextField(value = searchTerm,
        onValueChange = { onSearchTermChange(it) },
        keyboardActions = KeyboardActions(onSearch = { focusManager.clearFocus() }),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text, imeAction = ImeAction.Search
        ),
        singleLine = true,
        modifier = modifier,
        label = { Text(text = label, fontStyle = FontStyle.Italic) },
        leadingIcon = { Icon(painter = painterResource(R.drawable.ic_search), "Search Icon") },
        trailingIcon = {
            IconButton(onClick = { focusManager.clearFocus(); onClearTap() }) {
                Icon(painter = painterResource(R.drawable.ic_clear), "Clear Icon")
            }
        })
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FilmRow(
    film: Film,
    onFilmTap: (Film) -> Unit,
    removeFilm: (Film) -> Unit,
    editFilm: (Film) -> Unit,
    modifier: Modifier = Modifier,
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
            .combinedClickable(onClick = { onFilmTap(film) },
                onDoubleClick = { showDeleteDialog.value = true },
                onLongClick = { showEditDialog.value = true })
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