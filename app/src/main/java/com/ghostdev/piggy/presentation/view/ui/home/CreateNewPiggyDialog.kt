package com.ghostdev.piggy.presentation.view.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ghostdev.piggy.R
import com.ghostdev.piggy.data.database.PiggyModel
import com.ghostdev.piggy.presentation.theme.colorOne
import com.ghostdev.piggy.presentation.theme.cursorColor
import com.ghostdev.piggy.presentation.view.viewmodel.CalendarViewModel
import com.ghostdev.piggy.presentation.view.viewmodel.ColorViewModel
import com.ghostdev.piggy.presentation.view.viewmodel.LocalPiggyViewModel
import com.ghostdev.piggy.utils.removeCommas

@Composable
fun CreateNewPiggy(onDismiss: () -> Unit, viewModel: ColorViewModel = viewModel(), calendarViewModel: CalendarViewModel = viewModel()) {
    val piggyViewModel = LocalPiggyViewModel.current

    var piggyName by remember { mutableStateOf("") }
    var saved by remember { mutableStateOf("") }
    var goal by remember { mutableStateOf("") }
    var deadline by remember { mutableStateOf("Deadline (Optional)") }
    val color by remember { mutableStateOf("Color") }
    val selectedColor by viewModel.selectedColor.collectAsState()
    val deadlineDate by calendarViewModel.selectedDate.collectAsState()


    var colorDialogShow by remember { mutableStateOf(false) }
    var calendarDialogShow by remember { mutableStateOf(false) }

    if (deadlineDate.isNotEmpty()) {
        deadline = deadlineDate
    }

    piggyViewModel.getAllPiggyBanks()

    Dialog(onDismissRequest = {
        viewModel.resetColor()
        onDismiss()
        calendarViewModel.selectDate("")}) {
        Card(
            modifier = Modifier
                .wrapContentHeight(),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFEEF1ED) // Adjust color as needed
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Add new Piggy bank",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    fontSize = 19.sp
                )

                OutlinedTextField(
                    value = piggyName,
                    onValueChange = { piggyName = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp),
                    label = { Text(text = "Piggy Bank Name") },
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color.Gray,
                        focusedBorderColor = Color.Black,
                        cursorColor = cursorColor,
                        focusedLabelColor = Color.Black,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black
                    )
                )

                OutlinedTextField(
                    value = saved,
                    onValueChange = { saved = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp),
                    label = { Text(text = "Saved") },
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color.Gray,
                        focusedBorderColor = Color.Black,
                        cursorColor = cursorColor,
                        focusedLabelColor = Color.Black,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )

                OutlinedTextField(
                    value = goal,
                    onValueChange = { goal = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    label = { Text(text = "Goal (Optional)") },
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color.Gray,
                        focusedBorderColor = Color.Black,
                        cursorColor = cursorColor,
                        focusedLabelColor = Color.Black,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )

                OutlinedTextField(
                    value = deadline,
                    onValueChange = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { calendarDialogShow = true }
                        .padding(bottom = 10.dp),
                    enabled = false,
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color.Gray,
                        focusedBorderColor = Color.Black,
                        cursorColor = cursorColor,
                        focusedLabelColor = Color.Black,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        disabledBorderColor = Color.Gray,
                        disabledTextColor = Color.Black,
                        disabledTrailingIconColor = Color.Gray
                    ),
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.calendar),
                            contentDescription = "Calendar",
                            modifier = Modifier
                                .size(48.dp)
                                .padding(8.dp)
                        )
                    }
                )

                OutlinedTextField(
                    value = color,
                    onValueChange = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { colorDialogShow = true }
                        .padding(bottom = 30.dp),
                    shape = RoundedCornerShape(12.dp),
                    enabled = false,
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color.Gray,
                        focusedBorderColor = Color.Black,
                        cursorColor = cursorColor,
                        focusedLabelColor = Color.Black,
                        focusedTextColor = Color.Black,
                        disabledBorderColor = Color.Gray,
                        disabledTextColor = Color.Black
                    ),
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.color_box),
                            contentDescription = "Color Box",
                            modifier = Modifier
                                .size(48.dp)
                                .padding(8.dp),
                            tint = selectedColor ?: colorOne
                        )
                    },
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = "Discard",
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .clickable {
                                onDismiss()
                                calendarViewModel.selectDate("")
                                viewModel.resetColor()
                            },
                        fontSize = 16.sp,
                        color = Color.Gray // Adjust color as needed
                    )
                    Text(text = "Save",
                        fontSize = 16.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(end = 16.dp)
                            .clickable {
                                if (piggyName.isNotEmpty() && saved.isNotEmpty() && selectedColor != null && deadline.isNotEmpty() && goal.isNotEmpty()) {
                                    val piggyBank = PiggyModel(0, piggyName, saved.removeCommas().toDouble(), goal.removeCommas().toDouble(), deadline, selectedColor!!.toArgb(), false)
                                    piggyViewModel.createPiggy(piggyBank)
                                    piggyViewModel.getAllPiggyBanks()
                                    onDismiss()
                                    calendarViewModel.selectDate("")
                                    viewModel.resetColor()
                                }
                            }
                        )
                }
            }
        }
    }
    if (colorDialogShow) {
        ColorDialog(onDismiss = {colorDialogShow = false})
    }
    if (calendarDialogShow) {
        CalendarDialog(onDismiss = {calendarDialogShow = false})
    }
}