package com.example.dvdlibrary.composables

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun EditAlertDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    filmName: String,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(text = "OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "Cancel")
            }
        },
        text = { Text(text = "Edit ${filmName}?") }
    )
}