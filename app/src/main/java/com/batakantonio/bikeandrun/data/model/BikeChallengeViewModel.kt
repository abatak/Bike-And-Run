package com.batakantonio.bikeandrun.data.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.batakantonio.bikeandrun.data.ChallengesRepositoryImpl
import com.batakantonio.bikeandrun.data.database.BikeChallenges
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class BikeChallengeViewModel(
    private val challengesRepository: ChallengesRepositoryImpl
) : ViewModel() {

    fun fetchBikeChallenges(): Flow<List<BikeChallenges>> {
        return challengesRepository.getBikeChallenges()
    }

    fun createBikeChallenge(bikeChallenges: List<BikeChallenges>) {
        val challenges = bikeChallenges
        viewModelScope.launch {
            challengesRepository.createBikeChallenge(challenges)
        }
    }

    fun updateBikeChallenge(bikeChallenges: List<BikeChallenges>) {
        viewModelScope.launch {
            challengesRepository.updateBikeChallenge(bikeChallenges)
        }
    }
}