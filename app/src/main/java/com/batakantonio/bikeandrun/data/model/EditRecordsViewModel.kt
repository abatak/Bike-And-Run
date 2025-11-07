package com.batakantonio.bikeandrun.data.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.batakantonio.bikeandrun.data.RecordRepository
import com.batakantonio.bikeandrun.data.database.Record
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class EditRecordsViewModel(private val repository: RecordRepository) : ViewModel() {

    fun fetchRecords(): Flow<List<Record>> {
        return repository.getRecords()

    }

    fun createRecord(title: String, time: String?) {
        val record = Record(
            title = title,
            time = time
        )
        viewModelScope.launch {
            repository.createRecord(record)
        }
    }

    fun deleteAllRecords() {
        viewModelScope.launch {
            repository.deleteAllRecords()
        }
    }

    fun updateRecord(record: Record) {
        viewModelScope.launch {
            repository.updateRecord(record)
        }
    }

    fun deleteRecord(record: Record) {
        viewModelScope.launch {
            repository.deleteRecord(record)
        }
    }
}