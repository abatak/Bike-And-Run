package com.batakantonio.bikeandrun.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "running_challenges")
data class RunningChallenges(
    @PrimaryKey(autoGenerate = true) val challengeId: Int = 0,
    val title: String,
    val isChecked: Boolean = false
)
