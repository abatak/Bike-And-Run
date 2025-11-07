package com.batakantonio.bikeandrun

import com.batakantonio.bikeandrun.data.ChallengesRepositoryImpl
import com.batakantonio.bikeandrun.data.database.BikeChallenges
import com.batakantonio.bikeandrun.data.model.BikeChallengeViewModel
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
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class ChallengeBikeViewModelTest {

    private lateinit var viewModel: BikeChallengeViewModel
    private val repository: ChallengesRepositoryImpl = mock()
    private val testDispatcher = StandardTestDispatcher()


    @Before
    fun setup() {
        viewModel = BikeChallengeViewModel(repository)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun fetchBikeChallenges_should_return_list_from_repository() = runTest {

        // Given
        val mockChallenge = listOf(
            BikeChallenges(challengeId = 1, title = "Beginner ride 30 min"),
            BikeChallenges(challengeId = 2, title = "30-Minute climb ride")
        )

        // When
        whenever(repository.getBikeChallenges()).thenReturn(flowOf(mockChallenge))


        // Then
        val challenges = repository.getBikeChallenges().first()

        assertEquals(2, challenges.size)
        assertEquals("30-Minute climb ride", challenges[1].title)

    }


    @Test
    fun createBikeChallenges_should_return_list_from_repository() = runTest {

        val mockChallenges = listOf(
            BikeChallenges(challengeId = 1, "30-Minute dance cardio ride"),
            BikeChallenges(challengeId = 2, "45-Minute beginner ride"),
        )

        viewModel.createBikeChallenge(mockChallenges)

        advanceUntilIdle()

        verify(repository).createBikeChallenge(mockChallenges)
    }

    @Test
    fun updateBikeChallenge_should_return_list_from_repository() = runTest {

        val mockChallenge = BikeChallenges(title = "40-Minute intermediate ride")
        repository.updateBikeChallenge( listOf(mockChallenge))

        advanceUntilIdle()

        verify(repository).updateBikeChallenge(listOf(mockChallenge))
    }
}