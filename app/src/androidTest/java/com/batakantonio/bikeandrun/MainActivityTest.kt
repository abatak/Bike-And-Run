package com.batakantonio.bikeandrun

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.batakantonio.bikeandrun.data.firebase.auth.SignInActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

        @get:Rule
        val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setup() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

        @Test
        fun testBottomNavigationBike() {
            // Click on Bike icon
            onView(withId(R.id.ic_bike)).perform(click())

            // Check that the correct fragment is displayed
            onView(withId(R.id.fragment_container_bike)) // or any view in that fragment
                .check(matches(isDisplayed()))
        }

        @Test
        fun testBottomNavigationRunning() {
            // Click on Running icon
            onView(withId(R.id.ic_running)).perform(click())

            // Check that the correct fragment is displayed
            onView(withId(R.id.fragment_container_running))
                .check(matches(isDisplayed()))
        }

        @Test
        fun testOtherNavigation() {
            // Click on some other menu item (if applicable)
            onView(withId(R.id.ic_account)).perform(click())

            // Check that SignInActivity is opened
            intended(hasComponent(SignInActivity::class.java.name))
        }
    }

