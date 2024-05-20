package com.ghostdev.piggy.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDate(milliseconds: Long): String {
    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val date = Date(milliseconds)
    return sdf.format(date)
}