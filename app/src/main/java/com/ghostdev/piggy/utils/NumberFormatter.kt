package com.ghostdev.piggy.utils

import android.util.Log
import java.text.DecimalFormat

fun String.formatDecimalSeparator(): String {
    return this
        .replace(",", "")
        .reversed()
        .chunked(3)
        .joinToString(",")
        .reversed()
}