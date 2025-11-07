package com.batakantonio.bikeandrun

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.batakantonio.bikeandrun.ui.bicycle.BikeCaloriesFragment
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BikeCaloriesFragmentTest {

 private lateinit var scenario: FragmentScenario<BikeCaloriesFragment>

 @Before
 fun setup() {
     scenario = launchFragmentInContainer(themeResId = R.style.Theme_BikeAndRun)
     scenario.moveToState(Lifecycle.State.STARTED)
 }

    @Test
    fun calculateCalorie_validInput_showsResult() {
        val kg = 80.0
        val km = 10.0
        onView(withId(R.id.et_input_lb_or_kg))
            .perform(typeText(kg.toString()))
        onView(withId(R.id.et_input_mi_or_km))
            .perform(typeText(km.toString()))
        onView(withId(R.id.et_input_time))
            .perform(typeText("00:45:00"))
        Espresso.closeSoftKeyboard()

        // Click on "Calculate" button
        onView(withId(R.id.btn_calculate_bike))
            .perform(click())

        // Checking result
        onView(withId(R.id.tv_calorie_result_cycling))
            .check(matches(isDisplayed()))

    }

    @Test
    fun calculateCalorie_invalidInput_showsError() {

        val km = 90.0
        // Enter inputs
        onView(withId(R.id.et_input_lb_or_kg))
            .perform(typeText(""))
        onView(withId(R.id.et_input_mi_or_km))
            .perform(typeText(km.toString()))
        onView(withId(R.id.et_input_time))
            .perform(typeText("01:00:00"))

        Espresso.closeSoftKeyboard()

        // Click on "Calculate" button
        onView(withId(R.id.btn_calculate_bike))
            .perform(click())

        // Checking result, expecting false - Please enter valid input
        onView(withId(R.id.tv_calorie_result_cycling))
            .check(matches(withText("Please enter valid input.")))

    }

    @Test
    fun calculateCalorie_invalidTimeInput_showsError() {

        val kg = 70.0
        val km = 8.0
        // Enter inputs
        onView(withId(R.id.et_input_lb_or_kg))
            .perform(typeText(kg.toString()))
        onView(withId(R.id.et_input_mi_or_km))
            .perform(typeText(km.toString()))
        onView(withId(R.id.et_input_time))
            .perform(typeText(""))

        Espresso.closeSoftKeyboard()

        // Click on "Calculate" button
        onView(withId(R.id.btn_calculate_bike))
            .perform(click())

        // Checking result, expecting false - Please enter valid inputs
        onView(withId(R.id.tv_calorie_result_cycling))
            .check(matches(withText("Please enter valid inputs.")))
    }
}