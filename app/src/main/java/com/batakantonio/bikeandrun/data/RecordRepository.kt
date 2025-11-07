package com.batakantonio.bikeandrun.data

import com.batakantonio.bikeandrun.data.database.Record
import com.batakantonio.bikeandrun.data.database.RecordDao
import kotlinx.coroutines.flow.Flow

class RecordRepository(private val recordDao: RecordDao) {

  suspend  fun createRecord(record: Record) {
        recordDao.createRecord(record)
    }

    fun getRecords(): Flow<List<Record>> {
        return recordDao.getAllRecords()
    }

    suspend fun deleteAllRecords() {
        recordDao.deleteAllRecord()
    }

   suspend fun updateRecord(record: Record) {
        recordDao.updateRecord(record)
    }

   suspend fun deleteRecord(record: Record) {
        recordDao.deleteRecord(record)
    }

}