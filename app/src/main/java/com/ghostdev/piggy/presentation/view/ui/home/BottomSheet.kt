package com.ghostdev.piggy.presentation.view.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ghostdev.piggy.presentation.theme.leftButtonColor
import com.ghostdev.piggy.presentation.theme.rightButtonColor
import com.ghostdev.piggy.presentation.view.viewmodel.BottomSheetViewModel

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