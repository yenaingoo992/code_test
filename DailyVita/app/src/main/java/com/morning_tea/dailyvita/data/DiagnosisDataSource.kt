package com.morning_tea.dailyvita.data

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DiagnosisDataSource @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun readJsonFromAssets(fileName: String): String {
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
    }
}