package com.batakantonio.bikeandrun.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface RunningChallengeDao {

    @Insert
    suspend fun createRunningChallenge(runningChallenges: List<RunningChallenges>)

    @Query("SELECT * FROM running_challenges")
    fun getAllRunningChallenges(): Flow<List<RunningChallenges>>

    @Update
    suspend fun updateRunningChallenge(runningChallenges: List<RunningChallenges>)

}