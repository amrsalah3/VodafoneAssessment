package com.vodafone.assignment.data.weather.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

internal object DayFormatter {

    fun getDayName(afterNumberOfDays: Int): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, afterNumberOfDays)

        val futureDate = calendar.time
        return SimpleDateFormat("EEEE", Locale.getDefault()).format(futureDate)
    }
}
