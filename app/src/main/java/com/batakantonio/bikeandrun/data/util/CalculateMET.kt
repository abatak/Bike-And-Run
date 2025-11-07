package com.batakantonio.bikeandrun.data.util

object CalculateMET {

    // MET = metabolic equivalent of task ->
    // is a unit that estimates the energy cost of physical activity compared to resting
    fun estimateMET(speedKph: Double): Double {
        return when {
            speedKph < 4 -> 2.0   // slow walking
            speedKph < 6 -> 3.5   // normal walking
            speedKph < 8 -> 5.0   // brisk walking
            speedKph < 12 -> 7.0  // light jogging
            speedKph < 16 -> 9.0  // running
            else -> 12.0          // fast running
        }
    }
}