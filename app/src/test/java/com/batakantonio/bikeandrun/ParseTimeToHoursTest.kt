package com.batakantonio.bikeandrun

import com.batakantonio.bikeandrun.data.util.ParseTimeInHours
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull


class ParseTimeToHoursTest {

    @Test
    fun parseTimeToHours_validTime_returnsCorrectValue() {

        // Given
        val time = "01:30:25"

        // When
        val result = ParseTimeInHours.parseTimeToHours(time)

        // Then
        // 30 min / 60.0 = 0.5
        // 25s / 3600. = 0.00694
        // Total = 1 + 0.5 + 0.00694 = 1.50694 ~ 1.507
        assertEquals(1.507, result!!,0.001)
    }


    @Test
    fun parseTimeToHours_allZeros_returnZero() {
        val time = "00:00:00"
        val result = ParseTimeInHours.parseTimeToHours(time)
        assertEquals(0.0, result!!, 0.001)
    }

    @Test
    fun parseTimeToHours_invalidFormat_returnsNull() {
        val result = ParseTimeInHours.parseTimeToHours("45:30")
        assertNull(result)
    }

    @Test
    fun parseTimeToHours_nonNumeric_returnsNull() {
        val result = ParseTimeInHours.parseTimeToHours("aa:BB:cc")
        assertNull(result)
    }

}