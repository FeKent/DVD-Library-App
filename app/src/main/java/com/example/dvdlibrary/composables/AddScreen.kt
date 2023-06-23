package com.example.dvdlibrary.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import java.util.jar.Attributes
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment

@Composable
fun AddScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        AddTextField(name = "Title")
        AddTextField(name = "Runtime")
        AddTextField(name = "Year")
        AddTextField(name = "Director")
        AddTextField(name = "Genre")
    }
}

@Composable
fun AddTextField(name: String, modifier: Modifier = Modifier) {

    var addition by remember { mutableStateOf("") }

    TextField(
        value = addition,
        onValueChange = {addition = it},
        label = { Text(text = name)},
    )

}