package com.example.dvdlibrary.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dvdlibrary.R

@Composable
fun AddScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.padding(8.dp))
        Text(text = "Add Film Details", fontSize = 28.sp, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.padding(32.dp))
        AddTextField(name = "Title")
        AddTextField(name = "Runtime")
        AddTextField(name = "Year")
        AddTextField(name = "Director")
        AddTextField(name = "Genre")
        Spacer(modifier = Modifier.padding(24.dp))
        Row {
            SearchFilms()
            Spacer(modifier = Modifier.padding(24.dp))
            AddFilms()
        }
    }
}

@Composable
fun AddTextField(name: String, modifier: Modifier = Modifier) {

    var addition by remember { mutableStateOf("") }

    TextField(
        value = addition,
        onValueChange = { addition = it },
        label = { Text(text = name) },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
    )
}

@Composable
fun SearchFilms() {
    FloatingActionButton(onClick = { /*TODO*/ }, content = ({
        Icon(
            painter = painterResource(R.drawable.ic_search),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onBackground
        )
    }))
}

@Composable
fun AddFilms() {
    FloatingActionButton(onClick = { /*TODO*/ }, content = ({
        Icon(
            painter = painterResource(R.drawable.ic_add),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onBackground
        )
    }))
}
