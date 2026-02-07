package co.com.alexis.weather.ui.util

import android.util.Log
import java.text.SimpleDateFormat
import java.util.Locale

fun String.toDayName(): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val date = inputFormat.parse(this)
        val outputFormat = SimpleDateFormat("EEEE d", Locale.ENGLISH)
        date?.let { outputFormat.format(it) } ?: this
    } catch (exception: Exception) {
        Log.e("Pase Date", exception.message ?: "Unknown error")
        this
    }
}