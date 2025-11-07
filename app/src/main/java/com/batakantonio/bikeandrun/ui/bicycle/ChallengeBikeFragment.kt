package com.batakantonio.bikeandrun.ui.bicycle

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.batakantonio.bikeandrun.data.database.BikeChallenges
import com.batakantonio.bikeandrun.data.model.BikeChallengeViewModel
import com.batakantonio.bikeandrun.databinding.FragmentChallengeBikeBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChallengeBikeFragment : Fragment(), ChallengeOnItemListener {

    private val viewModel: BikeChallengeViewModel by viewModel()
    private lateinit var binding: FragmentChallengeBikeBinding
    private val adapter = ChallengeBikeAdapter(this)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentChallengeBikeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewBike.adapter = adapter
        fetchAllChallenges()

        // Navigate from ChallengeBikeFragment to BikeFragment
        binding.challengeBikeToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        // Ensures challenges are added only once
        lifecycleScope.launch {
            if (viewModel.fetchBikeChallenges().first().isEmpty()) {
                // '.first()' - once the first list is emitted, '.first()' returns it and stop collecting
                createChallenge()
            }
        }
    }

    private fun createChallenge() {
        viewModel.createBikeChallenge(
            bikeChallenges = listOf(
                BikeChallenges(title = "1. 30-Minute beginner ride"),
                BikeChallenges(title = "2. 15-Minute hit sprints ride"),
                BikeChallenges(title = "3. REST DAY"),
                BikeChallenges(title = "4. 20-Minute rhythm ride"),
                BikeChallenges(title = "5. 30-Minute climb ride"),
                BikeChallenges(title = "6. REST DAY"),
                BikeChallenges(title = "7. 40-Minute beginner ride"),
                BikeChallenges(title = "8. 20-Minute hit sprints ride"),
                BikeChallenges(title = "9. REST DAY"),
                BikeChallenges(title = "10. 30-Minute dance cardio ride"),
                BikeChallenges(title = "11. 25-Minute hit sprints ride"),
                BikeChallenges(title = "12. REST DAY"),
                BikeChallenges(title = "13. 45-Minute beginner ride"),
                BikeChallenges(title = "14. 30-Minute rhythm ride"),
                BikeChallenges(title = "15. REST DAY"),
                BikeChallenges(title = "16. 30-Minute sprints ride"),
                BikeChallenges(title = "17. 40-Minute intermediate ride"),
                BikeChallenges(title = "18. REST DAY"),
                BikeChallenges(title = "19. 45-Minute intermediate climb ride"),
                BikeChallenges(title = "20. 30-Minute dance cardio ride"),
                BikeChallenges(title = "21. REST DAY"),
                BikeChallenges(title = "22. 50-Minute rhythm ride"),
                BikeChallenges(title = "23. 40-Minute sprints ride"),
                BikeChallenges(title = "24. REST DAY"),
                BikeChallenges(title = "25. 50-Minute intermediate ride"),
                BikeChallenges(title = "26. 40-Minute dance cardio ride"),
                BikeChallenges(title = "27. REST DAY"),
                BikeChallenges(title = "28. 40-Minute sprints ride"),
                BikeChallenges(title = "29. REST DAY"),
                BikeChallenges(title = "30. 60-Minute sprints ride")
            )
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun fetchAllChallenges() {
        lifecycleScope.launch {
            viewModel.fetchBikeChallenges().collectLatest { challenges ->
                adapter.setBikeChallenges(challenges)

            }
        }
    }
    override fun onChallengeBikeUpdated(bikeChallenges: List<BikeChallenges>) {
        viewModel.updateBikeChallenge(bikeChallenges)
    }
}



















