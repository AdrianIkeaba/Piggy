package com.ghostdev.piggy.utils

fun calculatePercentage(amount: String, goal: String): String {
    val percentage = (amount.toDouble() / goal.toDouble()) * 100
    return percentage.toString()
}