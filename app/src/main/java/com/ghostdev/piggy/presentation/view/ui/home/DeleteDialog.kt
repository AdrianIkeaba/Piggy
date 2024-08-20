package com.ghostdev.piggy.presentation.view.ui.home

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.ghostdev.piggy.R
import com.ghostdev.piggy.data.database.PiggyModel
import com.ghostdev.piggy.presentation.view.viewmodel.PiggyViewModel

@Composable
fun DeleteDialog(onDismiss: () -> Unit, piggyData: PiggyModel, piggyViewModel: PiggyViewModel) {
    AlertDialog(
        icon = {
            Icon(
                painter = painterResource(id = R.drawable.delete),
                contentDescription = "Example Icon"
            )
        },
        title = {
            Text(text = "Delete Piggy?")
        },
        text = {
            Text(text = "Are you sure you want to delete this piggy?")
        },
        onDismissRequest = {
            onDismiss()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    piggyViewModel.deletePiggy(piggyData)
                    onDismiss()
                }
            ) {
                Text("Delete")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismiss()
                }
            ) {
                Text("Dismiss")
            }
        }
    )
}