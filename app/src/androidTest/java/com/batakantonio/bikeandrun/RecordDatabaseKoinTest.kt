package com.batakantonio.bikeandrun

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.batakantonio.bikeandrun.data.RecordRepository
import com.batakantonio.bikeandrun.data.database.Record
import com.batakantonio.bikeandrun.data.util.testDatabaseModule
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@RunWith(AndroidJUnit4::class)
class RecordDatabaseKoinTest : KoinTest {

    private val repository: RecordRepository by inject()

    @Before
    fun setup() {
        stopKoin() // In case test is running
        startKoin {
            androidContext(ApplicationProvider.getApplicationContext())
            modules(testDatabaseModule)
        }
    }

    @After
    fun tearDown() {
        stopKoin() // stop running
    }


    @Test
    fun insert_and_retrieve_records_from_koin_database() = runTest {

        // Given
        val testRecord = Record(title = "10km", time = "50:00")

        // When
        repository.createRecord(testRecord)


        // Then
        val result = repository.getRecords().first()
        assertEquals("10km", result[0].title)
    }


    @Test
    fun updateRecord_from_koin_database() = runTest {

        // Given
        val testRecord = Record(recordId = 1, title = "Cycling 20km", time = "1:00:00")
        repository.createRecord(testRecord)

        // When
        val updated = testRecord.copy(title = "Cycling 25km")
        repository.updateRecord(updated)

        // Then
        val allRecords = repository.getRecords().first()
        val updatedRecord = allRecords.find { it.recordId == testRecord.recordId }

        assertEquals("Cycling 25km", updatedRecord?.title)

    }


    @Test
    fun deleteAllRecords_from_koin_database() = runTest {

        val testRecord = Record(title = "Cycling 15 km", time = "45:00")
        repository.createRecord(testRecord)

        repository.deleteAllRecords()

        val allRecords = repository.getRecords().first()
        assertTrue(allRecords.isEmpty())

    }

    @Test
    fun deleteRecord_from_koin_database() = runTest {

        val testRecord = Record(recordId = 1, title = "Running 10km", time = "45:00")
        repository.createRecord(testRecord)

        repository.deleteRecord(testRecord)

        val allRecords = repository.getRecords().first()

        assertTrue(allRecords.isEmpty())
    }
}