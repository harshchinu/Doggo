package com.example.dogs.view.dogList

import android.app.Application
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.dogs.R
import com.example.dogs.view.MainActivity
import kotlinx.coroutines.awaitAll
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Pattern.matches

@RunWith(AndroidJUnit4ClassRunner::class)
class ListFragmentTest{
    @Test
    fun testNavigationtoDogDetailFrag() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        navController.setGraph(R.navigation.dog_navigation)

        val scenario = launchFragmentInContainer<ListFragment>()

        scenario.onFragment {
            Navigation.setViewNavController(it.requireView(),navController)
        }


        


       // onView(ViewMatchers.withId(R.id.dogsList)).perform(ViewActions.click())

        //onView(ViewMatchers.withId(R.id.dogsList)).perform(ViewActions.click())

        //assertTha(navController.currentDestination?.id).equals(R.id.detailFragment))*/

   /*     val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        FragmentScenario.launchInContainer(ListFragment::class.java)



      //  onView(ViewMatchers.withId(R.id.dogsList)).perform(ViewActions.click())*/


    }


}



