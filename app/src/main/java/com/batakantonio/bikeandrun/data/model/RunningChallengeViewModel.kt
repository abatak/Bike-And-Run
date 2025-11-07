package com.batakantonio.bikeandrun.data.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.batakantonio.bikeandrun.data.ChallengesRepositoryImpl
import com.batakantonio.bikeandrun.data.database.RunningChallenges
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class RunningChallengeViewModel(
    private var challengesRepository: ChallengesRepositoryImpl
) : ViewModel() {

    fun fetchRunningChallenges(): Flow<List<RunningChallenges>> {
        return challengesRepository.getRunningChallenges()
    }

    fun createRunningChallenges(runningChallenges: List<RunningChallenges>) {
        val challenges = runningChallenges
        viewModelScope.launch {
            challengesRepository.createRunningChallenge(challenges)
        }
    }

    fun updateChallenge(runningChallenges: List<RunningChallenges>) {
        viewModelScope.launch {
            challengesRepository.updateRunningChallenge(runningChallenges)
        }
    }
}
