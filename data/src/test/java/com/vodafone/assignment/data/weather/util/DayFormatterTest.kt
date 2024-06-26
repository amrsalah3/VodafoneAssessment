package com.vodafone.assignment.data.weather.util

import org.junit.Test
import java.time.LocalDate
import kotlin.test.assertEquals

internal class DayFormatterTest {

    @Test
    fun `test day name after a number of days from today`() {
        val expectedDay = LocalDate.now()
            .dayOfWeek
            .plus(5)
            .name
            .lowercase()
            .replaceFirstChar { it.uppercaseChar() }

        val actualDay = DayFormatter.getDayName(5)

        assertEquals(expectedDay, actualDay)
    }
}
