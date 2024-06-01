package com.ghostdev.piggy.presentation.view.ui.home

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ghostdev.piggy.R
import com.ghostdev.piggy.data.database.PiggyModel
import com.ghostdev.piggy.presentation.theme.colorEight
import com.ghostdev.piggy.presentation.theme.colorEightForeground
import com.ghostdev.piggy.presentation.theme.colorFive
import com.ghostdev.piggy.presentation.theme.colorFiveForeground
import com.ghostdev.piggy.presentation.theme.colorFour
import com.ghostdev.piggy.presentation.theme.colorFourForeground
import com.ghostdev.piggy.presentation.theme.colorNine
import com.ghostdev.piggy.presentation.theme.colorNineForeground
import com.ghostdev.piggy.presentation.theme.colorOne
import com.ghostdev.piggy.presentation.theme.colorOneForeground
import com.ghostdev.piggy.presentation.theme.colorSeven
import com.ghostdev.piggy.presentation.theme.colorSevenForeground
import com.ghostdev.piggy.presentation.theme.colorSix
import com.ghostdev.piggy.presentation.theme.colorSixForeground
import com.ghostdev.piggy.presentation.theme.colorThree
import com.ghostdev.piggy.presentation.theme.colorThreeForeground
import com.ghostdev.piggy.presentation.theme.colorTwo
import com.ghostdev.piggy.presentation.theme.colorTwoForeground
import com.ghostdev.piggy.presentation.theme.cursorColor
import com.ghostdev.piggy.presentation.view.customcomposables.WavesLevelIndicator
import com.ghostdev.piggy.presentation.view.viewmodel.LocalPiggyViewModel
import com.ghostdev.piggy.presentation.view.viewmodel.PiggyViewModel
import com.ghostdev.piggy.utils.formatDecimalSeparator


@Composable
fun PiggyBankCard(piggyData: PiggyModel) {
    val piggyViewModel = LocalPiggyViewModel.current
    var showDeleteDialog by remember {
        mutableStateOf(false)
    }
    val piggyName = piggyData.piggyName
    val amountSaved = piggyData.amountSaved
    val goal = piggyData.goal
    val remaining = (goal - amountSaved).toString()
    val deadline = piggyData.deadlineDate
    val color = piggyData.color
    val colorWave = when (color) {
        colorOne.toArgb() -> colorOneForeground.toArgb()
        colorTwo.toArgb() -> colorTwoForeground.toArgb()
        colorThree.toArgb() -> colorThreeForeground.toArgb()
        colorFour.toArgb() -> colorFourForeground.toArgb()
        colorFive.toArgb() -> colorFiveForeground.toArgb()
        colorSix.toArgb() -> colorSixForeground.toArgb()
        colorSeven.toArgb() -> colorSevenForeground.toArgb()
        colorEight.toArgb() -> colorEightForeground.toArgb()
        colorNine.toArgb() -> colorNineForeground.toArgb()

        else -> colorOne.toArgb()
    }

    var dropDownStateDeadline by remember {
        mutableStateOf(false)
    }
    var dropDownStateQuickEdit by remember {
        mutableStateOf(false)
    }
    var pinState by remember {
        mutableStateOf(false)
    }
    var amount by remember {
        mutableStateOf("")
    }

    val progress = remember {
        mutableDoubleStateOf(0.0)
    }
    LaunchedEffect(piggyData.amountSaved) {
        progress.doubleValue = amountSaved.toDouble() / goal.toDouble()
    }

    Spacer(modifier = Modifier.height(12.dp))
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .wrapContentHeight(),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(2.dp, Color.Black),
        colors = CardDefaults.cardColors(
            containerColor = Color(color)
        )
    ) {
        Box(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
        ) {
                WavesLevelIndicator(
                    modifier = Modifier.matchParentSize(),
                    color = Color(colorWave),
                    progress = progress.doubleValue.toFloat()
                )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize(tween(300, easing = LinearOutSlowInEasing))
                    .wrapContentHeight()
            ) {
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 8.dp)
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = piggyName,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 8.dp)
                    )

                    if (pinState) {
                        IconButton(onClick = { pinState = !pinState
                            piggyViewModel.unpinPiggy(piggyData)
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.pin_filled),
                                contentDescription = "Pin",
                                tint = Color.Black
                            )
                        }
                    } else {
                        IconButton(onClick = { pinState = !pinState
                            piggyViewModel.pinPiggy(piggyData) }) {
                            Icon(
                                painter = painterResource(id = R.drawable.pin_outlined),
                                contentDescription = "Pin",
                                tint = Color.Black
                            )
                        }
                    }

                }
                Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    Text(
                        text = "₦${amountSaved.toString().formatDecimalSeparator()} / ₦${goal.toString().formatDecimalSeparator()}",
                        fontSize = 18.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    Text(
                        text = "Remaining: ₦${remaining.formatDecimalSeparator()}",
                        fontSize = 18.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                if (dropDownStateDeadline) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(text = "Deadline: $deadline", fontSize = 17.sp, color = Color.Black)
                        Image(
                            painter = painterResource(id = R.drawable.arrow_up),
                            contentDescription = "drop down arrow",
                            modifier = Modifier
                                .padding(start = 8.dp, end = 20.dp)
                                .clickable {
                                    dropDownStateDeadline = !dropDownStateDeadline
                                }
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Save ₦500.0/day", fontSize = 17.sp, color = Color.Black)
                    }
                } else {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(text = "Deadline: $deadline", fontSize = 17.sp, color = Color.Black)
                        Icon(
                            painter = painterResource(id = R.drawable.dropdown_arrow),
                            contentDescription = "drop down arrow",
                            tint = Color.Black,
                            modifier = Modifier
                                .padding(start = 8.dp, end = 20.dp)
                                .clickable {
                                    dropDownStateDeadline = !dropDownStateDeadline
                                }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                if (dropDownStateQuickEdit) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(text = "Quick Edit", fontSize = 17.sp, color = Color.Black)
                        Image(
                            painter = painterResource(id = R.drawable.arrow_up),
                            contentDescription = "drop down arrow",
                            modifier = Modifier
                                .padding(start = 8.dp, end = 20.dp)
                                .clickable {
                                    dropDownStateQuickEdit = !dropDownStateQuickEdit
                                }
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        OutlinedTextField(
                            value = amount, onValueChange = { amount = it },
                            label = { Text(text = "Amount") },
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = Color.Black,
                                focusedBorderColor = Color.Black,
                                cursorColor = cursorColor,
                                focusedLabelColor = Color.Black,
                                unfocusedLabelColor = Color.Black,
                                focusedTextColor = Color.Black
                            )
                        )

                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            onClick = {
                                if (amount.isNotEmpty()) {
                                    val difference = piggyData.amountSaved - amount.toInt()
                                    if (difference < 0.0) {
                                        val updatedPiggy = PiggyModel(piggyData.id, piggyName, 0.0, piggyData.goal, piggyData.deadlineDate, piggyData.color, false)
                                        piggyViewModel.updatePiggy(updatedPiggy)
                                        piggyViewModel.getAllPiggyBanks()
                                        amount = ""
                                    } else {
                                        val updatedPiggy = PiggyModel(piggyData.id, piggyName, difference, piggyData.goal, piggyData.deadlineDate, piggyData.color, false)
                                        piggyViewModel.updatePiggy(updatedPiggy)
                                        piggyViewModel.getAllPiggyBanks()
                                        amount = ""
                                    }

                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .padding(start = 55.dp),
                            shape = RoundedCornerShape(8.dp),
                            border = BorderStroke(2.dp, Color.Black),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFF07D64)
                            )
                        ) {
                            Text(
                                text = "Minus",
                                color = Color.Black,
                                modifier = Modifier.padding(4.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(4.dp))
                        Button(
                            onClick = {
                                      if (amount.isNotEmpty()) {
                                          val updatedPiggy = PiggyModel(piggyData.id, piggyName, piggyData.amountSaved + amount.toInt(), piggyData.goal, piggyData.deadlineDate, piggyData.color, false)
                                          piggyViewModel.updatePiggy(updatedPiggy)
                                          piggyViewModel.getAllPiggyBanks()
                                          amount = ""
                                      }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 55.dp),
                            shape = RoundedCornerShape(8.dp),
                            border = BorderStroke(2.dp, Color.Black),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF99D191)
                            )
                        ) {
                            Text(
                                text = "Add",
                                color = Color.Black,
                                modifier = Modifier.padding(4.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                } else {
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(text = "Quick Edit", fontSize = 17.sp, color = Color.Black)
                        Image(
                            painter = painterResource(id = R.drawable.dropdown_arrow),
                            contentDescription = "drop down arrow",
                            modifier = Modifier
                                .padding(start = 8.dp, end = 20.dp)
                                .clickable {
                                    dropDownStateQuickEdit = !dropDownStateQuickEdit
                                }
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp),
                    horizontalArrangement = Arrangement.Start
                )
                {
                    Image(
                        painter = painterResource(id = R.drawable.edit),
                        contentDescription = "Edit"
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Image(painter = painterResource(id = R.drawable.delete),
                        contentDescription = "Delete",
                        modifier = Modifier.clickable { showDeleteDialog = true })

                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
    }
    if (showDeleteDialog) {
        DeleteDialog(
            onDismiss = { showDeleteDialog = false },
            piggyData = piggyData,
            piggyViewModel = piggyViewModel
        )
    }
}
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