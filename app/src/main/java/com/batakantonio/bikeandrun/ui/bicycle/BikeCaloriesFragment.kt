package com.batakantonio.bikeandrun.ui.bicycle

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.batakantonio.bikeandrun.R
import com.batakantonio.bikeandrun.data.util.ParseTimeInHours
import com.batakantonio.bikeandrun.databinding.FragmentBikeCaloriesBinding


class BikeCaloriesFragment : Fragment() {

    private lateinit var binding: FragmentBikeCaloriesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentBikeCaloriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnCalculateBike.setOnClickListener {
                calculateCalorie()
            }

            caloriesBikeToolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    @SuppressLint("DefaultLocale", "SetTextI18n")
    private fun calculateCalorie() {
        binding.apply {
            val weightInput = etInputLbOrKg.text.toString().toDoubleOrNull()
            val distanceInput = etInputMiOrKm.text.toString().toDoubleOrNull()
            val timeString = etInputTime.text.toString()


            // Validate inputs
            if (weightInput == null || distanceInput == null) {
                tvCalorieResultCycling.text = "Please enter valid input."
                return
            }


            val weightInKg = when (rbGroupWeight.checkedRadioButtonId) {
                R.id.rb_lb -> weightInput * 2.2046 // 1 kg = 2.2046 lb
                else -> weightInput  // kg by default
            }

            val distanceInKm = when (rbGroupDistance.checkedRadioButtonId) {
                R.id.rb_miles -> distanceInput / 1.60934// 1 mile = 1.60934 km
                else -> distanceInput  // km by default
            }


            // Convert time to hours
            val timeInHours = ParseTimeInHours.parseTimeToHours(timeString)
            if (timeInHours == null || timeInHours <= 0.0) {
                tvCalorieResultCycling.text = "Please enter valid inputs."
                return
            }

            // Calculate speed
            val speed = distanceInput / timeInHours

            // approximately met for cycling
            val met = 8.4

            // Calories formula
            val caloriesBurned = met * weightInKg * timeInHours
            val formattedCalories = String.format("%.2f", caloriesBurned)

            tvCalorieResultCycling.text =
                "Cycling $distanceInKm km at %.2f kph at a weight of %.1f kg \n".format(
                    speed,
                    weightInKg
                ) +
                        "requires approximately $formattedCalories Calories(kcal)\n" +
                        "of energy.".trimEnd()
        }
    }
}






