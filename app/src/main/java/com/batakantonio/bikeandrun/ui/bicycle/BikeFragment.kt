package com.batakantonio.bikeandrun.ui.bicycle


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.batakantonio.bikeandrun.R
import com.batakantonio.bikeandrun.databinding.FragmentBikeBinding
import com.batakantonio.bikeandrun.ui.EditRecordsActivity
import com.batakantonio.bikeandrun.ui.maps.MapsActivity


class BikeFragment : Fragment() {

    private lateinit var binding: FragmentBikeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentBikeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setOnClickListeners()
        super.onViewCreated(view, savedInstanceState)
    }

   private fun setOnClickListeners() {
        binding.apply {
            // Navigate from BikeFragment to ChallengeBikeFragment
            cardViewChallenge.setOnClickListener {
                findNavController().navigate(R.id.action_bikeFragment_to_challengeBikeFragment)
            }

            // Navigate from BikeFragment to BikeCaloriesFragment
            cardViewCalories.setOnClickListener {
                findNavController().navigate(R.id.action_bikeFragment_to_caloriesBikeFragment)
            }

          cardViewTracking.setOnClickListener {
              val intent = Intent(context, MapsActivity::class.java)
              startActivity(intent)

          }

            cardViewRecordsBike.setOnClickListener {
                val intent = Intent(context, EditRecordsActivity::class.java)
                startActivity(intent)
            }
        }
    }
}





