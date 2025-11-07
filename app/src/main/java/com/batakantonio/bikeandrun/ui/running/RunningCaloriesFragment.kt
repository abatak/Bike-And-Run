package com.batakantonio.bikeandrun.ui.running

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.batakantonio.bikeandrun.R
import com.batakantonio.bikeandrun.data.util.CalculateMET
import com.batakantonio.bikeandrun.data.util.ParseTimeInHours
import com.batakantonio.bikeandrun.databinding.FragmentRunningCaloriesBinding


class RunningCaloriesFragment : Fragment() {

    private lateinit var binding: FragmentRunningCaloriesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentRunningCaloriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCalculateRunning.setOnClickListener {
            calculateCalories()
        }

        binding.caloriesRunningToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    @SuppressLint("SetTextI18n", "DefaultLocale")
    private fun calculateCalories() {
        binding.apply {
            val weightInput = etInputLbOrKg.text.toString().toDoubleOrNull()
            var distanceInput = etInputMiOrKm.text.toString().toDoubleOrNull()
            var durationInput = etInputTime.text.toString().trim()

            // Validate inputs
            if (weightInput == null || distanceInput == null) {
                tvCalorieResultRunning.text = "Please enter correct input"
                return
            }

            val weightInKg = when (rbGroupWeight.checkedRadioButtonId) {
                R.id.rb_lb -> weightInput * 2.2046// 1 kg = 2.2046 lb
                else -> weightInput // kg by default
            }

            val distanceInKm = when (rbGroupDistance.checkedRadioButtonId) {
                R.id.rb_miles -> distanceInput / 1.60934// 1 mile = 1.60934 km
                else -> distanceInput  // km by default
            }

            // Convert hours to time
            val timeInHours = ParseTimeInHours.parseTimeToHours(durationInput)
            if (timeInHours == null || timeInHours <= 0.0) {
                tvCalorieResultRunning.text = "Please enter valid inputs."
                return
            }

            // Calculate Speed
            val speed = distanceInKm / timeInHours

            // Calculate MET for running
            val met = CalculateMET.estimateMET(speed)

            // Calories formula
            val caloriesBurned = met * weightInKg * timeInHours
            val formattedCalories = String.format("%.2f", caloriesBurned)

            tvCalorieResultRunning.text =
                "Running $distanceInKm km at %.2f kph at a weight of %.1f kg \n".format(
                    speed,
                    weightInKg
                ) +
                        "requires approximately $formattedCalories Calories(kcal)\n" +
                        "of energy.".trimEnd()
        }
    }
}


