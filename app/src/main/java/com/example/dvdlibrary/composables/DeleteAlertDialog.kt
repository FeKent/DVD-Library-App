package com.example.dvdlibrary.composables

import android.app.AlertDialog
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@Composable
fun DeleteAlertDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
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
            title = { Text(text = "Delete film?")},
            text = { Text(text = "Press OK to delete film")}
        )
    }
}

class MainViewModel : ViewModel(){
    private val _showDialog = MutableStateFlow(false)
}