package com.batakantonio.bikeandrun.data.util

object ParseTimeInHours {

    fun parseTimeToHours(time: String): Double? {
        val parts = time.split(":")
        if (parts.size != 3) return null

        val hours = parts[0].toIntOrNull() ?: return null
        val minutes = parts[1].toIntOrNull() ?: return null
        val seconds = parts[2].toIntOrNull() ?: return null

        return hours + (minutes / 60.0) + (seconds / 3600.0)
    }
}