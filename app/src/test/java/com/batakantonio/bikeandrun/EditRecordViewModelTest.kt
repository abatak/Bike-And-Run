package com.batakantonio.bikeandrun

import com.batakantonio.bikeandrun.data.RecordRepository
import com.batakantonio.bikeandrun.data.database.Record
import com.batakantonio.bikeandrun.data.model.EditRecordsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals


@OptIn(ExperimentalCoroutinesApi::class)
class EditRecordViewModelTest {

    private lateinit var viewModel: EditRecordsViewModel

    // Fake repository
    private val repository: RecordRepository = mock()

    private val testDispatcher = StandardTestDispatcher()


    @Before
    fun setup() {
        viewModel = EditRecordsViewModel(repository)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun fetchRecords_should_return_list_from_repository() = runTest{


        val mockRecord = listOf(Record(1, "10km", "45:00"))

        whenever(repository.getRecords()).thenReturn(flowOf(mockRecord))


        val records = repository.getRecords().first()

        assertEquals(1, records.size)
        assertEquals("10km", records[0].title)

    }


    @Test
    fun createRecord_should_call_repository_with_correct_record() = runTest {

        // Given
        val title = "5km"
        val time = "25:00"


        // When
        viewModel.createRecord(title, time)

        // Wait until coroutine launched by viewModelScope finishes
        advanceUntilIdle()

        // Then
        val recordCaptor = argumentCaptor<Record>() // Creates a holder to capture arguments passed to mock
        verify(repository).createRecord(recordCaptor.capture())

        val captured = recordCaptor.firstValue
        assertEquals(title, captured.title)
        assertEquals(time, captured.time)

    }


    @Test
    fun deleteAllRecords_should_call_repository() = runTest {

        viewModel.deleteAllRecords()
        advanceUntilIdle()
        verify(repository).deleteAllRecords()
    }


    @Test
    fun updateRecord_should_call_repository() = runTest {

        val mockRecord = Record(title = "Marathon",  time = "4:00:00")

        viewModel.updateRecord(mockRecord)

        advanceUntilIdle()

        verify(repository).updateRecord(mockRecord)

    }


    @Test
    fun deleteRecord_should_call_repository() = runTest {

        val mockRecord = Record(title = "Half-Marathon", time =  "2:00:00" )

        viewModel.deleteRecord(mockRecord)

        advanceUntilIdle()

        verify(repository).deleteRecord(mockRecord)
    }


}

