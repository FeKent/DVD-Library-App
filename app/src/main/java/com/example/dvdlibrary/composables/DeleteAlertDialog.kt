package com.example.dvdlibrary.composables

import android.app.AlertDialog
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun DeleteAlertDialog() {
    AlertDialog(
        onDismissRequest = {},
        confirmButton = {
            TextButton(onClick = { /*TODO*/ }) {
                Text(text = "OK")
            }
        },
        dismissButton = {
            TextButton(onClick = { /*TODO*/ }) {
                Text(text = "Cancel")
            }
        },
        title = { Text(text = "Delete film?")},
        text = { Text(text = "Press OK to delete film")}
    )
}