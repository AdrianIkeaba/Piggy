package com.ghostdev.piggy.ui.view

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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ghostdev.piggy.R
import com.ghostdev.piggy.ui.theme.Inter
import com.ghostdev.piggy.ui.theme.KronaOne
import com.ghostdev.piggy.ui.theme.colorOne
import com.ghostdev.piggy.ui.theme.colorThree
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
import com.ghostdev.piggy.ui.view.viewmodel.BottomSheetViewModel
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(controller: NavController) {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = rememberStandardBottomSheetState(skipHiddenState = false))
    var showDialog by remember { mutableStateOf(false) }

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
                            onClick = { /* do something */ },
                            containerColor = tertiary,
                            elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                        ) {
                            Icon(Icons.Filled.Add, "Add PiggyBank", tint = Color.White,
                                modifier = Modifier.clickable { showDialog = true })
                        }
                    }
                )
            }
        }
    }
    if (showDialog) {
        CreateNewPiggy(onDismiss = { showDialog = false })
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
fun CreateNewPiggy(onDismiss: () -> Unit) {
    var piggyName by remember { mutableStateOf("") }
    var saved by remember { mutableStateOf("") }
    var goal by remember { mutableStateOf("") }
    var deadline by remember { mutableStateOf("Deadline (Optional)") }
    var color by remember { mutableStateOf("Color") }

    Dialog(onDismissRequest = { onDismiss() }) {
        Card(
            modifier = Modifier
                .wrapContentHeight()
                .padding(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = primary // Adjust color as needed
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
                        cursorColor = Color.Black,
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
                        cursorColor = Color.Black,
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
                        cursorColor = Color.Black,
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
                        .padding(bottom = 10.dp),
                    enabled = false,
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color.Gray,
                        focusedBorderColor = Color.Black,
                        cursorColor = Color.Black,
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
                        .padding(bottom = 30.dp),
                    shape = RoundedCornerShape(12.dp),
                    enabled = false,
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color.Gray,
                        focusedBorderColor = Color.Black,
                        cursorColor = Color.Black,
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
                            tint = colorThree
                        )
                    }
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = "Discard",
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .clickable { onDismiss() },
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
}

