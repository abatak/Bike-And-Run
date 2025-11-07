package com.batakantonio.bikeandrun

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.longClick
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasChildCount
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.batakantonio.bikeandrun.ui.EditRecordsActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EditRecordsActivityTest {
    @get:Rule
    val editRecordsActivityRule = ActivityScenarioRule(EditRecordsActivity::class.java)


    @Test
    fun addRecord_shows_in_recycler_view() {

        // Click on FAB bottom sheet
        onView(withId(R.id.fab_add_records)).perform(click())

        // Type record name
        onView(withId(R.id.et_record_name))
            .perform(typeText("Test Record"), closeSoftKeyboard())

        // Type record time
        onView(withId(R.id.et_time))
            .perform(typeText("25:00"), closeSoftKeyboard())

        // Click save
        onView(withId(R.id.saveBtn)).perform(click())

        // Check checkbox
        onView(withId(R.id.cb_record)).perform(click())

        // Check that RecyclerView shows the new record
        onView(withText("Test Record"))
            .check(matches(isDisplayed()))
    }


    @Test
    fun clearAllRecords_shows_empty_recyclerView() {

        // Open overflown menu
        openActionBarOverflowOrOptionsMenu(
            InstrumentationRegistry.getInstrumentation().targetContext
        ) // gives the Context of app under test


        // Click "Clear All Records"
        onView(withText("Clear All Records")).perform(click())

        // Confirm dialog
        onView(withText("Yes")).perform(click())


        Thread.sleep(5000)

        // Verify that RecyclerView is empty
        onView(withId(R.id.recycler_view_records))
            .check(matches(hasChildCount(0)))


    }

    @Test
    fun delete_single_record_shows_in_recycler_view() {

        // Click on FAB bottom sheet
        onView(withId(R.id.fab_add_records)).perform(click())

        // Type record name
        onView(withId(R.id.et_record_name))
            .perform(typeText("10km"), closeSoftKeyboard())

        // Type record time
        onView(withId(R.id.et_time))
            .perform(typeText("45:00"), closeSoftKeyboard())

        // Click "Save"
        onView(withId(R.id.saveBtn)).perform(click())

        // Perform delete
        onView(withText("10km")).perform(longClick())

        // Verify record no longer exists
        onView(withText("10km")).check(doesNotExist())

    }
}