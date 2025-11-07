package com.batakantonio.bikeandrun

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.batakantonio.bikeandrun.ui.EditRecordsActivity
import com.batakantonio.bikeandrun.ui.running.RunningFragment
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
class RunningFragmentTest {

    private lateinit var navController: TestNavHostController

    @Before
    fun setup() {
        Intents.init()
    }

    @After
    fun tearDown() {
        // Release Intents resources
        Intents.release()
    }

    @Test
    fun clicking_cardViewChallenge_navigates_and_shows_recyclerView_in_ChallengeRunningFragment() {

        navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        val scenario =
            launchFragmentInContainer<RunningFragment>(themeResId = R.style.Theme_BikeAndRun)

        scenario.onFragment { fragment ->
            navController.setGraph(R.navigation.nav_graph_running)
            navController.setCurrentDestination(R.id.runningFragment)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        // Click the card to navigate
        onView(withId(R.id.cardView_challenge)).perform(click())

        // Verify navController moved to the correct destination
        assertEquals(R.id.challengeRunningFragment, navController.currentDestination?.id)

    }

    @Test
    fun clicking_cardViewCalories_navigates_to_RunningCaloriesFragment() {

        navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        val scenario =
            launchFragmentInContainer<RunningFragment>(themeResId = R.style.Theme_BikeAndRun)

        scenario.onFragment { fragment ->
            navController.setGraph(R.navigation.nav_graph_running)
            navController.setCurrentDestination(R.id.runningFragment)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        // Click the card to navigate
        onView(withId(R.id.cardView_calories)).perform(click())

        // Verify navController moved to the correct destination
        assertEquals(R.id.caloriesRunningFragment, navController.currentDestination?.id)
    }

    @Test
    fun clicking_cardViewRecords_intent_to_EditRecordsActivity_and_shows_recyclerView() {

        // Launch fragment in container
        launchFragmentInContainer<RunningFragment>(themeResId = R.style.Theme_BikeAndRun)

        // Perform the click on the view that should start the Activity
        onView(withId(R.id.cardView_records)).perform(click())

        // Verify that an Intent for MapsActivity is launched
        intended(hasComponent(EditRecordsActivity::class.java.name))
    }


}