package com.example.dvdlibrary.composables

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable


@Composable
fun ValidationDialog(
    label: String,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = { onDismiss()},
        confirmButton = {
            TextButton(onClick = { onDismiss() }) { Text(text = "OK") }
        }, text = { Text(text = "$label field is not valid") }
    )
}