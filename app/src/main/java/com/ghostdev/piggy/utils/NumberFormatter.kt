package com.ghostdev.piggy.utils

import android.util.Log
import java.text.DecimalFormat

fun String.formatDecimalSeparator(): String {
    // Remove the dot and any trailing zeros after the dot
    val cleanedString = this.replace(Regex("\\.0+$"), "")

    // Remove existing commas and reformat the string
    val formattedString = cleanedString
        .replace(",", "")
        .reversed()
        .chunked(3)
        .joinToString(",")
        .reversed()

    return "$formattedString.0"
}

fun String.removeCommas(): Int {
    return this.replace(",", "").toInt()
}