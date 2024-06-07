package com.ghostdev.piggy.presentation.view.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ghostdev.piggy.R
import com.ghostdev.piggy.data.database.PiggyModel
import com.ghostdev.piggy.presentation.theme.Inter
import com.ghostdev.piggy.presentation.theme.KronaOne
import com.ghostdev.piggy.presentation.theme.primary
import com.ghostdev.piggy.presentation.theme.secondary
import com.ghostdev.piggy.presentation.theme.tertiary
import com.ghostdev.piggy.presentation.view.viewmodel.BottomSheetViewModel
import com.ghostdev.piggy.presentation.view.viewmodel.LocalPiggyViewModel
import com.ghostdev.piggy.utils.PreferencesHelper
import com.ghostdev.piggy.utils.formatDecimalSeparator
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(filterViewModel: BottomSheetViewModel = viewModel()) {
    PreferencesHelper.setFirstTime(LocalContext.current.applicationContext, false)
    val piggyViewModel = LocalPiggyViewModel.current
    val piggyList = piggyViewModel.piggyList.collectAsState()

    val totalAmount = remember { mutableDoubleStateOf(0.0) }
    val totalGoal = remember { mutableDoubleStateOf(0.0) }

    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(piggyList.value) {
        totalAmount.doubleValue = 0.0
        totalGoal.doubleValue = 0.0
        if (piggyList.value.isEmpty()) {
            totalAmount.doubleValue = 0.0
            totalGoal.doubleValue = 0.0
        }
        for (piggy in piggyList.value) {
            totalAmount.doubleValue += piggy.amountSaved
            totalGoal.doubleValue += piggy.goal
        }
        delay(50)
        isLoading = false
    }

    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = rememberStandardBottomSheetState(skipHiddenState = false))
    var createDialogShow by remember { mutableStateOf(false) }

    val selectedRightButton by filterViewModel.selectedRightButton.collectAsState()
    val selectedLeftButton by filterViewModel.selectedLeftButton.collectAsState()

    LaunchedEffect(selectedRightButton, selectedLeftButton) {
        when (selectedRightButton) {
            "Ascending" -> {
                when (selectedLeftButton) {
                    "Name" -> piggyViewModel.orderByNameAsc()
                    "Amount" -> piggyViewModel.orderByAmountAsc()
                    "Goal" -> piggyViewModel.orderByGoalAsc()
                    "Remaining" -> piggyViewModel.orderByRemainingAsc()
                }
            }
            "Descending" -> {
                when (selectedLeftButton) {
                    "Name" -> piggyViewModel.orderByNameDesc()
                    "Amount" -> piggyViewModel.orderByAmountDesc()
                    "Goal" -> piggyViewModel.orderByGoalDesc()
                    "Remaining" -> piggyViewModel.orderByRemainingDesc()
                }
            }
        }
    }

    BottomSheetScaffold(
        modifier = Modifier.pointerInput(Unit) {
            detectTapGestures(onTap = {
                scope.launch {
                    if (scaffoldState.bottomSheetState.isVisible) {
                        scaffoldState.bottomSheetState.hide()
                    }
                }
            })
        },
        sheetContainerColor = primary,
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            BottomSheetContent()
        }) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color.White)) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(top = 10.dp, start = 20.dp, end = 20.dp)) {
                Text(
                    text = "Saved ₦${totalAmount.doubleValue.toString().formatDecimalSeparator()} / ₦${totalGoal.doubleValue.toString().formatDecimalSeparator()}",
                    modifier = Modifier.align(Alignment.CenterVertically),
                    color = Color.Black,
                    fontFamily = Inter,
                    fontWeight = FontWeight.SemiBold
                )
            }

            if (isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.88f),
                    contentAlignment = Alignment.Center
                ) { }
            } else {
                if (piggyList.value.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.88f),
                        contentAlignment = Alignment.Center
                    ) {
                        Column {
                            Image(
                                painter = painterResource(id = R.drawable.no_piggy_banks),
                                contentDescription = "No Piggy Banks",
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                            Text(
                                text = "Create a new piggy bank to start saving!",
                                textAlign = TextAlign.Center,
                                color = Color.Black,
                                fontFamily = KronaOne,
                                modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                            )
                        }
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.88f)
                            .padding(16.dp)
                    ) {
                        LazyColumn {
                            items(piggyList.value.size) {
                                PiggyBankCard(piggyList.value[it])
                            }
                        }
                    }
                }
            }

            Box(modifier = Modifier.fillMaxHeight()) {
                BottomAppBar(
                    containerColor = secondary,
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
                            Icon(
                                Icons.Filled.Add,
                                "Add PiggyBank",
                                tint = Color.White,
                                modifier = Modifier.clickable { createDialogShow = true })
                        }
                    }
                )
            }
        }
    }
    if (createDialogShow) {
        CreateNewPiggy(
            onDismiss = { createDialogShow = false },
            piggyModel = PiggyModel(0, "", 0.0, 0.0, "", 0, false)
        )
    }
}
