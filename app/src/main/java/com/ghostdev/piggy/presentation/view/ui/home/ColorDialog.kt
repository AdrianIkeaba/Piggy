package com.ghostdev.piggy.presentation.view.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ghostdev.piggy.presentation.theme.colorEight
import com.ghostdev.piggy.presentation.theme.colorFive
import com.ghostdev.piggy.presentation.theme.colorFour
import com.ghostdev.piggy.presentation.theme.colorNine
import com.ghostdev.piggy.presentation.theme.colorOne
import com.ghostdev.piggy.presentation.theme.colorSeven
import com.ghostdev.piggy.presentation.theme.colorSix
import com.ghostdev.piggy.presentation.theme.colorThree
import com.ghostdev.piggy.presentation.theme.colorTwo
import com.ghostdev.piggy.presentation.view.viewmodel.ColorViewModel

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