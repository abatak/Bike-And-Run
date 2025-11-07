package com.batakantonio.bikeandrun.ui.running

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.batakantonio.bikeandrun.data.database.RunningChallenges
import com.batakantonio.bikeandrun.data.model.RunningChallengeViewModel
import com.batakantonio.bikeandrun.databinding.FragmentChallengeRunningBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class ChallengeRunningFragment : Fragment(),
    ChallengesRunningAdapter.ChallengeRunningOnItemListener {

    private val viewModel: RunningChallengeViewModel by viewModel()
    private lateinit var binding: FragmentChallengeRunningBinding
    private val adapter = ChallengesRunningAdapter(this)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChallengeRunningBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchAllChallenges()
        binding.recyclerViewRunning.adapter = adapter

        // Navigate back to RunningFragment
        binding.challengeRunningToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        // Ensures challenges are added only once
        lifecycleScope.launch {
            if (viewModel.fetchRunningChallenges().first().isEmpty()) {
                // '.first()' - once the first list is emitted, '.first()' returns it and stop collecting
                createChallenge()
            }
        }
    }

    private fun createChallenge() {
        viewModel.createRunningChallenges(
            runningChallenges = listOf(
                RunningChallenges(title = "1. 2km running for 20 minutes"),
                RunningChallenges(title = "2. 2 km walk"),
                RunningChallenges(title = "3. REST DAY"),
                RunningChallenges(title = "4. 1km sprints run (repeat 10 times)"),
                RunningChallenges(title = "5. 2km running for 18 minutes"),
                RunningChallenges(title = "6. REST DAY"),
                RunningChallenges(title = "7. 2km running for 18 minutes"),
                RunningChallenges(title = "8. 2.5km walk"),
                RunningChallenges(title = "9. REST DAY"),
                RunningChallenges(title = "10. 2.5km walk"),
                RunningChallenges(title = "11. 1km sprints run (repeat 10 times)"),
                RunningChallenges(title = "12. REST DAY"),
                RunningChallenges(title = "13. 2.5km walk"),
                RunningChallenges(title = "14. 2.5km running for 20 minutes"),
                RunningChallenges(title = "15. REST DAY"),
                RunningChallenges(title = "16. 31km sprints run (repeat 8 times)"),
                RunningChallenges(title = "17. 3km walk"),
                RunningChallenges(title = "18. REST DAY"),
                RunningChallenges(title = "19. 3km running for 18 minutes"),
                RunningChallenges(title = "20. 3km walk"),
                RunningChallenges(title = "21. REST DAY"),
                RunningChallenges(title = "22. 1km sprints run (repeat 7 times)"),
                RunningChallenges(title = "23. 3.5 km running for 17 minutes"),
                RunningChallenges(title = "24. REST DAY"),
                RunningChallenges(title = "25. 3.5km running for 15 minutes"),
                RunningChallenges(title = "26. 4km walk"),
                RunningChallenges(title = "27. REST DAY"),
                RunningChallenges(title = "28. 4km running for 20 minutes"),
                RunningChallenges(title = "29. REST DAY"),
                RunningChallenges(title = "30. 5km running for 25 minutes")
            )
        )
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun fetchAllChallenges() {
        lifecycleScope.launch {
            viewModel.fetchRunningChallenges().collectLatest { challenge ->
                adapter.setRunningChallenges(challenge)
            }
        }
    }

    override fun onChallengeRunningUpdated(runningChallenges: List<RunningChallenges>) {
        viewModel.updateChallenge(runningChallenges)
    }
}