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
import com.batakantonio.bikeandrun.ui.bicycle.BikeFragment
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BikeFragmentTest {

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
    fun clicking_cardViewChallenge_navigates_and_shows_recyclerView_in_ChallengeBikeFragment() {
        navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        val scenario = launchFragmentInContainer<BikeFragment>(
            themeResId = R.style.Theme_BikeAndRun
        )

        scenario.onFragment { fragment ->
            navController.setGraph(R.navigation.nav_graph_bicycle)
            navController.setCurrentDestination(R.id.bikeFragment)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        // Click the card to navigate
        onView(withId(R.id.cardView_challenge)).perform(click())

        // Verify navController moved to the correct destination
        assertEquals(R.id.challengeBikeFragment, navController.currentDestination?.id)
    }

    @Test
    fun clicking_cardViewCalories_navigates_to_BikeCaloriesFragment() {
        val scenario = launchFragmentInContainer<BikeFragment>(
            themeResId = R.style.Theme_BikeAndRun
        )

        navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        scenario.onFragment { fragment ->
            navController.setGraph(R.navigation.nav_graph_bicycle)
            navController.setCurrentDestination(R.id.bikeFragment)

            // Attach NavController to fragment
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        // Click to card to navigate
        onView(withId(R.id.cardView_calories)).perform(click())

        // Verify navController moved to the correct destination
        assertEquals(R.id.caloriesBikeFragment, navController.currentDestination?.id)
    }

    @Test
    fun clicking_cardViewRecords_intent_to_EditRecordsActivity_and_shows_recyclerView() {

        // Launch fragment in a container
         launchFragmentInContainer<BikeFragment>(
            themeResId = R.style.Theme_BikeAndRun
        )

        // Perform the click on the view that should start the Activity
        onView(withId(R.id.cardView_records_bike)).perform(click())

        // Verify that an Intent for EditRecordsActivity is launched
        intended(hasComponent(EditRecordsActivity::class.java.name))
    }


}



