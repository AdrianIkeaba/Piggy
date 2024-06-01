package com.ghostdev.piggy.presentation.view.ui.home

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ghostdev.piggy.presentation.view.viewmodel.CalendarViewModel
import com.ghostdev.piggy.utils.formatDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarDialog(onDismiss: () -> Unit, viewModel: CalendarViewModel = viewModel()) {
    val datePickerState = rememberDatePickerState()
    val currentDateMillis = System.currentTimeMillis()

    val dateValidator: (Long?) -> Boolean = { date ->
        date != null && date >= currentDateMillis
    }
    val confirmEnabled = remember {
        derivedStateOf { datePickerState.selectedDateMillis != null && dateValidator(datePickerState.selectedDateMillis) }
    }

    DatePickerDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(onClick = {
                if (datePickerState.selectedDateMillis != null) {
                    viewModel.selectDate(formatDate(datePickerState.selectedDateMillis!!))
                    onDismiss()
                }
            }, enabled = confirmEnabled.value) {
                Text(text = "Confirm", fontSize = 16.sp, color = Color.Black)
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(text = "Cancel", fontSize = 16.sp, color = Color.Black)
            }
        }
    ) {
        DatePicker(state = datePickerState, dateValidator = dateValidator)
    }
}