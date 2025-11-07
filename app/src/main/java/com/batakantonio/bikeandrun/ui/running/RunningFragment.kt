package com.batakantonio.bikeandrun.ui.running

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.batakantonio.bikeandrun.R
import com.batakantonio.bikeandrun.databinding.FragmentRunningBinding
import com.batakantonio.bikeandrun.ui.EditRecordsActivity
import com.batakantonio.bikeandrun.ui.maps.MapsActivity


class RunningFragment : Fragment() {


    private lateinit var binding: FragmentRunningBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRunningBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setOnClickListeners()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setOnClickListeners() {
        binding.apply {
            // Navigate from RunningFragment to ChallengeRunningFragment
            cardViewChallenge.setOnClickListener {
                findNavController().navigate(R.id.action_runningFragment_to_challengeRunningFragment)
            }
            // Navigate from RunningFragment to RunningCaloriesFragment
            cardViewCalories.setOnClickListener {
                findNavController().navigate(R.id.action_runningFragment_to_caloriesRunningFragment)
            }

            cardViewTracking.setOnClickListener {
                val intent = Intent(context, MapsActivity::class.java)
                startActivity(intent)
            }

            cardViewRecords.setOnClickListener {
                val intent = Intent(context, EditRecordsActivity::class.java)
                startActivity(intent)
            }
        }
    }
}