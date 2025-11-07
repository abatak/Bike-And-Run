package com.batakantonio.bikeandrun.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bike_challenges")
data class BikeChallenges(
    @PrimaryKey(autoGenerate = true) val challengeId: Int = 0,
    val title: String,
    val isChecked: Boolean = false
)
