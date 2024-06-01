package com.ghostdev.piggy.utils

import java.util.Locale

fun calculatePercentage(amount: String, goal: String): String {
    val percentage = (amount.toDouble() / goal.toDouble()) * 100
    return String.format(Locale.US, "%.2f", percentage)
}