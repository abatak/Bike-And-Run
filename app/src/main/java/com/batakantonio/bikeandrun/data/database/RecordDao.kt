package com.batakantonio.bikeandrun.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface RecordDao {

    @Insert
    suspend fun createRecord(record: Record)

    @Query("SELECT * FROM record")
    fun getAllRecords(): Flow<List<Record>>

    @Query("DELETE FROM record")
    suspend fun deleteAllRecord()

    @Update
    suspend fun updateRecord(record: Record)

    @Delete
    suspend fun deleteRecord(record: Record)
}