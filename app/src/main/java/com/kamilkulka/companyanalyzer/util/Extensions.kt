package com.kamilkulka.companyanalyzer.util

import timber.log.Timber
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun String.convertToDate(): LocalDate? {
    val patterns = listOf("dd/MM/yyyy", "d/M/yyyy")

    for (pattern in patterns) {
        try {
            return LocalDate.parse(this, DateTimeFormatter.ofPattern(pattern))
        } catch (e: Exception) {
            Timber.d("convertToDate() exception: ${e.localizedMessage}")
        }
    }
    return null
}

fun String.separateInt(): Int {
    val regex = Regex("[^0-9]")
    return this.replace(regex, "").toInt()
}