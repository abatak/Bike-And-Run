package com.batakantonio.bikeandrun.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "record")
data class Record(
  @PrimaryKey(autoGenerate = true)  val recordId: Int = 0,
    val title: String,
    val time: String? = null,
    val isChecked: Boolean = false

)

