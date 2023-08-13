package com.dolphinmobile.moviesapp.util

import java.text.SimpleDateFormat
import java.util.Locale

object DateFormat {

    fun String.formatDate(pattern: String): String {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = format.parse(this)
        return SimpleDateFormat(pattern, Locale.US)
            .format(date?.time)
    }
}