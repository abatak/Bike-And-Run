package com.batakantonio.bikeandrun.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BikeChallengeDao {

    @Insert
    suspend fun createBikeChallenge(bikeChallenges: List<BikeChallenges>)

    @Query("SELECT * FROM bike_challenges")
    fun getAllBikeChallenges() :Flow<List<BikeChallenges>>

    @Update
    suspend fun updateBikeChallenge(bikeChallenges: List<BikeChallenges>)

}