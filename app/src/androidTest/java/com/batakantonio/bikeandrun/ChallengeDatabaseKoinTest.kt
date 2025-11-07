package com.batakantonio.bikeandrun

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.batakantonio.bikeandrun.data.ChallengesRepositoryImpl
import com.batakantonio.bikeandrun.data.database.BikeChallenges
import com.batakantonio.bikeandrun.data.database.RunningChallenges
import com.batakantonio.bikeandrun.data.util.testDatabaseModule
import junit.framework.TestCase.assertEquals
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

@RunWith(AndroidJUnit4::class)
class ChallengeDatabaseKoinTest : KoinTest {

    private val repository: ChallengesRepositoryImpl by inject()


    @Before
    fun setup() {
        stopKoin()
        startKoin {
            androidContext(ApplicationProvider.getApplicationContext())
            modules(testDatabaseModule)
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun insert_and_read_bike_challenges() = runTest {

        // Given
        val testChallenges = listOf(
            BikeChallenges( challengeId = 1, title = "30-Minute beginner ride"),
            BikeChallenges(challengeId = 2 ,title = "15-Minute hit sprints ride")
        )

        // When
        repository.createBikeChallenge(testChallenges)

        // Then
        val results = repository.getBikeChallenges().first()
        // Inserted 2 challenges
        assertEquals(2, results.size)
        assertEquals("30-Minute beginner ride", results[0].title)
        assertEquals("15-Minute hit sprints ride", results[1].title)
    }


    @Test
    fun insert_and_read_running_challenges() = runTest {

        // Given
        val testChallenges = listOf(
            RunningChallenges( challengeId = 1 ,title = "2km running for 20 minutes"),
            RunningChallenges(challengeId = 2,title = "2 km walk")
        )

        // When
        repository.createRunningChallenge(testChallenges)


        // Then
        val results = repository.getRunningChallenges().first()
        // Inserted 2 challenges
        assertEquals(2, results.size)
        assertEquals("2km running for 20 minutes", results[0].title)
        assertEquals("2 km walk", results[1].title)

    }

    @Test
    fun update_running_challenges_in_koin_database() = runTest {

        // Given
        val testChallenge = listOf(
            RunningChallenges( challengeId = 1,title = "1km sprints run (repeat 10 times)"),
            RunningChallenges( challengeId = 2,title = "45-Minute beginner ride"),
        )
        repository.createRunningChallenge(testChallenge)

        // When
        val updated = listOf(
            testChallenge.first().copy( challengeId = 2 ,title = "30-Minute dance cardio ride")
        )
        repository.updateRunningChallenge(updated)

        // Then
        val allChallenges = repository.getRunningChallenges().first()
        val updatedChallenge = allChallenges.find { it.challengeId == 2 }

        assertEquals("30-Minute dance cardio ride", updatedChallenge?.title)

    }

    @Test
    fun update_bike_challenges_in_koin_database() = runTest {

        val testChallenges = listOf(
            BikeChallenges(challengeId = 1, title = "30-Minute climb ride" ),
            BikeChallenges(challengeId = 2, title = "20-Minute hit sprints ride" )
        )
        repository.createBikeChallenge(testChallenges)

        val updated = listOf(
            testChallenges.first().copy(challengeId = 2, "60-Minute sprints ride")
        )
        repository.updateBikeChallenge(updated)

        val allChallenges = repository.getBikeChallenges().first()
        val updatedChallenge = allChallenges.find { it.challengeId == 2 }

        assertEquals("60-Minute sprints ride", updatedChallenge?.title)
    }
}