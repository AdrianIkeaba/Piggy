package com.ghostdev.piggy.ui.view

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ghostdev.piggy.R
import com.ghostdev.piggy.ui.theme.Inter
import com.ghostdev.piggy.ui.theme.KronaOne
import com.ghostdev.piggy.ui.theme.colorEight
import com.ghostdev.piggy.ui.theme.colorFive
import com.ghostdev.piggy.ui.theme.colorFiveForeground
import com.ghostdev.piggy.ui.theme.colorFour
import com.ghostdev.piggy.ui.theme.colorNine
import com.ghostdev.piggy.ui.theme.colorNineForeground
import com.ghostdev.piggy.ui.theme.colorOne
import com.ghostdev.piggy.ui.theme.colorSeven
import com.ghostdev.piggy.ui.theme.colorSix
import com.ghostdev.piggy.ui.theme.colorThree
import com.ghostdev.piggy.ui.theme.colorThreeForeground
import com.ghostdev.piggy.ui.theme.colorTwo
import com.ghostdev.piggy.ui.theme.colorTwoForeground
import com.ghostdev.piggy.ui.theme.cursorColor
import com.ghostdev.piggy.ui.theme.leftButtonColor
import com.ghostdev.piggy.ui.theme.premiumPrimary
import com.ghostdev.piggy.ui.theme.premiumSecondary
import com.ghostdev.piggy.ui.theme.primary
import com.ghostdev.piggy.ui.theme.rightButtonColor
import com.ghostdev.piggy.ui.theme.secondary
import com.ghostdev.piggy.ui.theme.tertiary
import com.ghostdev.piggy.ui.view.customcomposables.AutoResizeText
import com.ghostdev.piggy.ui.view.customcomposables.FontSizeRange
import com.ghostdev.piggy.ui.view.customcomposables.WavesLevelIndicator
import com.ghostdev.piggy.ui.view.viewmodel.BottomSheetViewModel
import com.ghostdev.piggy.ui.view.viewmodel.CalendarViewModel
import com.ghostdev.piggy.ui.view.viewmodel.ColorViewModel
import com.ghostdev.piggy.utils.formatDate
import com.ghostdev.piggy.utils.formatDecimalSeparator
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(controller: NavController) {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = rememberStandardBottomSheetState(skipHiddenState = false))
    var createDialogShow by remember { mutableStateOf(false) }

    BottomSheetScaffold(
        modifier = Modifier.pointerInput(Unit) {
            detectTapGestures( onTap = {
                scope.launch {
                    if (scaffoldState.bottomSheetState.isVisible) {
                        scaffoldState.bottomSheetState.hide()
                    }
                }
            })
        },
        sheetContainerColor = primary,
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,// Set initial peek height to 0
        sheetContent = {
            BottomSheetContent()
        }) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color.White))
        {
            Row(modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(top = 10.dp, start = 20.dp, end = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AutoResizeText(text = "Saved ₦0/₦0",
                    maxLines = 1,
                    modifier = Modifier.align(Alignment.CenterVertically),
                    fontSizeRange = FontSizeRange(min = 10.sp, max = 18.sp),
                    color = Color.Black,
                    fontFamily = Inter,
                    fontWeight = FontWeight.SemiBold
                )
                Button(onClick = { },
                    modifier = Modifier.wrapContentWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = premiumSecondary),
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(2.dp, premiumPrimary)
                ) {
                    Text(text = "Go Premium!",
                        modifier = Modifier.padding(5.dp),
                        color = Color.Black,
                        fontWeight = FontWeight.Normal
                    )
                }
            }

            Box(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.88f),
                contentAlignment = Alignment.Center
            ) {
                Column {
                        Image(painter = painterResource(id = R.drawable.no_piggy_banks),
                            contentDescription = "No Piggy Banks",
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                        Text(text = "Create a new piggy bank to start saving!",
                            textAlign = TextAlign.Center,
                            color = Color.Black,
                            fontFamily = KronaOne,
                            modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                        )
                }
            }

            Box(modifier = Modifier.fillMaxHeight()) {
                BottomAppBar(containerColor = secondary,
                    actions = {
                        IconButton(onClick = {
                            scope.launch {
                                    scaffoldState.bottomSheetState.expand()
                            }
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.sort),
                                contentDescription = "Sort",
                                tint = Color.Black
                            )
                        }
                        IconButton(onClick = { /* doSomething() */ }) {
                            Icon(Icons.Outlined.Settings, contentDescription = "Settings", tint = Color.Black)
                        }
                    },
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = { createDialogShow = true },
                            containerColor = tertiary,
                            elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                        ) {
                            Icon(Icons.Filled.Add, "Add PiggyBank", tint = Color.White, modifier = Modifier.clickable { createDialogShow = true })
                        }
                    }
                )
            }
        }
    }
    if (createDialogShow) {
        CreateNewPiggy(onDismiss = { createDialogShow = false } )
    }
}

@Composable
fun BottomSheetContent(viewModel: BottomSheetViewModel = viewModel()) {
    val selectedLeftButton by viewModel.selectedLeftButton.collectAsState()
    val selectedRightButton by viewModel.selectedRightButton.collectAsState()

    Row(modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 40.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Left Buttons
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val leftButtons = listOf("Name", "Amount", "Goal", "Remaining")
            leftButtons.forEach { text ->
                Button(
                    onClick = { viewModel.selectLeftButton(text) },
                    Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = if (selectedLeftButton == text) leftButtonColor else Color.Transparent),
                    border = BorderStroke(2.dp, Color.Black),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(text = text, color = Color.Black, modifier = Modifier.padding(5.dp))
                }
            }
        }

        // Right Buttons
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val rightButtons = listOf("Ascending", "Descending")
            rightButtons.forEach { text ->
                Button(
                    onClick = { viewModel.selectRightButton(text) },
                    Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = if (selectedRightButton == text) rightButtonColor else Color.Transparent),
                    border = BorderStroke(2.dp, Color.Black),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(text = text, color = Color.Black, modifier = Modifier.padding(5.dp))
                }
            }
        }
    }
}

@Composable
fun CreateNewPiggy(onDismiss: () -> Unit, viewModel: ColorViewModel = viewModel(), calendarViewModel: CalendarViewModel = viewModel()) {
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
                    onValueChange = { saved = it.formatDecimalSeparator() },
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
                    onValueChange = { goal = it.formatDecimalSeparator() },
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
                    onValueChange = {  },
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
                    Text(
                        text = "Save",
                        modifier = Modifier.padding(end = 16.dp),
                        fontSize = 16.sp,
                        color = Color.Gray // Adjust color as needed
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

@Composable
fun ColorDialog(onDismiss: () -> Unit, viewModel: ColorViewModel = viewModel()) {
    val selectedColor by viewModel.selectedColor.collectAsState()

    Dialog(onDismissRequest = {
        onDismiss() }) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFEEF1ED)
            )
        ) {
            val colors = listOf(
                colorOne, colorTwo, colorThree,
                colorFour, colorFive, colorSix,
                colorSeven, colorEight, colorNine
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                items(colors) { color ->
                    Card(
                        modifier = Modifier
                            .size(100.dp)
                            .padding(8.dp)
                            .clickable {
                                viewModel.selectColor(color)
                                onDismiss()
                            },
                        colors = CardDefaults.cardColors(
                            containerColor = color
                        ),
                        border = if (selectedColor == color) {
                            BorderStroke(2.dp, Color.Black)
                        } else {
                            BorderStroke(2.dp, Color.Transparent)
                        }
                    ) {}
                }
            }
        }
    }
}

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

@Composable
@Preview
fun PiggyBank() {
    var dropDownStateDeadline by remember {
        mutableStateOf(false)
    }
    var dropDownStateQuickEdit by remember {
        mutableStateOf(false)
    }
    var amount by remember {
        mutableStateOf("")
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(2.dp, Color.Black),
        colors = CardDefaults.cardColors(
            containerColor = colorFive
        )
    ) {
        Box(
            modifier = Modifier
                .wrapContentHeight()
                .padding(bottom = 16.dp)
                .fillMaxWidth()
        ) {

            WavesLevelIndicator(modifier = Modifier.matchParentSize(), color = colorFiveForeground, progress = 0.34f)

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize(tween(150, easing = FastOutSlowInEasing))
                    .wrapContentHeight()
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Piggy Name",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )

                    IconButton(onClick = { }) {
                        Icon(
                            painter = painterResource(id = R.drawable.pin_outlined),
                            contentDescription = "Pin",
                            tint = Color.Black
                        )
                    }
                }
                Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    Text(
                        text = "₦20,000 / ₦55,000" + " (36.36%)",
                        fontSize = 18.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    Text(
                        text = "Remaining: ₦35,000",
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
                        Text(text = "Deadline: 27/05/2024", fontSize = 17.sp, color = Color.Black)
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
                        Text(text = "Deadline: 27/05/2024", fontSize = 17.sp, color = Color.Black)
                            Icon(
                                painter = painterResource(id = R.drawable.dropdown_arrow),
                                contentDescription = "drop down arrow",
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
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
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
                            onClick = { },
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .padding(start = 55.dp),
                            shape = RoundedCornerShape(8.dp),
                            border = BorderStroke(2.dp, Color.Black),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFF07D64))
                        ) {
                            Text(text = "Minus", color = Color.Black, modifier = Modifier.padding(4.dp))
                        }
                        Spacer(modifier = Modifier.width(4.dp))
                        Button(
                            onClick = { },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 55.dp),
                            shape = RoundedCornerShape(8.dp),
                            border = BorderStroke(2.dp, Color.Black),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF99D191))
                        ) {
                            Text(text = "Add", color = Color.Black, modifier = Modifier.padding(4.dp))
                        }
                    }
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
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                    horizontalArrangement = Arrangement.Start)
                {
                    Image(painter = painterResource(id = R.drawable.edit), contentDescription = "Edit")
                    Spacer(modifier = Modifier.width(16.dp))
                    Image(painter = painterResource(id = R.drawable.delete), contentDescription = "Delete")
                }
            }
        }
    }
}