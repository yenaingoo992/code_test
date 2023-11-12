package com.morning_tea.moviecorner.utils

import java.text.DecimalFormat

fun Double.formatDecimalString(format: String = "#0.0"): String = DecimalFormat(format).format(this)