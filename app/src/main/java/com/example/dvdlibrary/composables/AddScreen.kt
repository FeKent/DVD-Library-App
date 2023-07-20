package com.example.dvdlibrary.composables

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.ExposedDropdownMenuDefaults.TrailingIcon
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dvdlibrary.R
import com.example.dvdlibrary.data.Film
import com.example.dvdlibrary.data.Genre
import java.lang.Exception
import java.time.LocalDate

@Composable
fun AddScreen(onFilmEntered: (Film) -> Unit, backButton: () -> Unit, modifier: Modifier = Modifier) {

    val mContext = LocalContext.current
    var title by remember { mutableStateOf("") }
    var runTime by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }
    var director by remember { mutableStateOf("") }
    var genre by remember { mutableStateOf(Genre.Action) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.padding(8.dp))
        Text(text = "Add Film Details", fontSize = 28.sp, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.padding(32.dp))
        AddTextField(label = "Title", value = title, onValueChange = { title = it })
        AddNumField(label = "Runtime", value = runTime, onValueChange = { runTime = it })
        AddNumField(label = "Year", value = year, onValueChange = { year = it })
        AddTextField(label = "Director", value = director, onValueChange = { director = it })
        AddGenreField(selectedItem = genre, onGenreSelected = { genre = it })
        Spacer(modifier = Modifier.padding(24.dp))

        Row {
            BackButton {
                backButton()
            }
            Spacer(modifier = Modifier.padding(horizontal = 16.dp))
            AddFilms(onSaveTap = {

                val yearIsValid: Boolean = try {
                    val intYear = year.toInt()
                    intYear > 1900 && intYear < LocalDate.now().year+1
                } catch (e:Exception){
                    false
                }

                if (runTime.isEmpty() || title.isEmpty() || director.isEmpty() || !yearIsValid ){
                    Toast.makeText(mContext, "Not All Fields Used", Toast.LENGTH_SHORT).show()
                    return@AddFilms
                }

                onFilmEntered(
                    Film(
                        id = 0,
                        runTime.toInt(),
                        title,
                        R.drawable.generic_poster,
                        "",
                        year.toInt(),
                        director,
                        genre
                    )
                )
            })
        }
    }
}

@Composable
fun AddTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {

    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            capitalization = KeyboardCapitalization.Words
        )
    )
}


@Composable
fun AddNumField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            capitalization = KeyboardCapitalization.Words,
            keyboardType = KeyboardType.Number
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddGenreField(selectedItem: Genre, onGenreSelected: (Genre) -> Unit) {


    var expanded by remember { mutableStateOf(false) }


    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
        TextField(modifier = Modifier.menuAnchor(),
            value = selectedItem.printName,
            onValueChange = {},
            readOnly = true,
            label = {
                Text(text = "Genre")
            },
            trailingIcon = {
                TrailingIcon(expanded = expanded)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )

        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            Genre.values().forEach { option ->
                DropdownMenuItem(
                    { Text(text = option.printName) },
                    onClick = {
                        onGenreSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}


@Composable
fun AddFilms(onSaveTap: () -> Unit) {
    FloatingActionButton(onClick = { onSaveTap() }, content = ({
        Icon(
            painter = painterResource(R.drawable.ic_add),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onBackground
        )
    }))
}

@Composable
fun BackButton(onBackTap: () -> Unit) {
    FloatingActionButton(onClick = { onBackTap() }, content = ({
        Icon(
            painter = painterResource(R.drawable.ic_back),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onBackground
        )
    }))
}