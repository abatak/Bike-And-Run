package com.batakantonio.bikeandrun.data

import com.batakantonio.bikeandrun.data.database.BikeChallengeDao
import com.batakantonio.bikeandrun.data.database.BikeChallenges
import com.batakantonio.bikeandrun.data.database.RunningChallengeDao
import com.batakantonio.bikeandrun.data.database.RunningChallenges
import kotlinx.coroutines.flow.Flow

class ChallengesRepositoryImpl(
    private val bikeChallengeDao: BikeChallengeDao,
    private val runningChallengeDao: RunningChallengeDao
) {

    suspend fun createBikeChallenge(bikeChallenges: List<BikeChallenges>) {
        bikeChallengeDao.createBikeChallenge(bikeChallenges)
    }

    suspend fun createRunningChallenge(runningChallenges: List<RunningChallenges>) {
        runningChallengeDao.createRunningChallenge(runningChallenges)
    }

    fun getBikeChallenges(): Flow<List<BikeChallenges>> {
        return bikeChallengeDao.getAllBikeChallenges()
    }

    fun getRunningChallenges(): Flow<List<RunningChallenges>> {
        return runningChallengeDao.getAllRunningChallenges()
    }

    suspend fun updateBikeChallenge(bikeChallenges: List<BikeChallenges>) {
        bikeChallengeDao.updateBikeChallenge(bikeChallenges)
    }

    suspend fun updateRunningChallenge(runningChallenges: List<RunningChallenges>) {
        runningChallengeDao.updateRunningChallenge(runningChallenges)
    }
}