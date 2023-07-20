package com.example.dvdlibrary.composables

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import com.example.dvdlibrary.data.Film
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun DeleteAlertDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    filmName: String,
) {
    if (show){
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
            text = { Text(text = "Delete ${filmName}?")}
        )
    }
}
