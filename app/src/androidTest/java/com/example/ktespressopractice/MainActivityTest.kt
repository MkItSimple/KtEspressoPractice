package com.example.ktespressopractice

import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.graphics.drawable.toBitmap
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test


class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_isListFragmentVisible_onAppLaunch() {
        onView(withId(R.id.username)).check(matches(isDisplayed()))

        onView(withId(R.id.image_view)).check(matches(withDrawable(R.drawable.c2)))
    }

    // https://medium.com/@miloszlewandowski/espresso-matcher-for-imageview-made-easy-with-android-ktx-977374ca3391
    fun withDrawable(@DrawableRes id: Int) = object : TypeSafeMatcher<View>() {

        override fun describeTo(description: Description) {
            description.appendText("ImageView with drawable same as drawable with id $id")
        }

        override fun matchesSafely(view: View): Boolean {
            val context = view.context
            val expectedBitmap = context.getDrawable(id)!!.toBitmap()
            return view is ImageView && view.drawable.toBitmap().sameAs(expectedBitmap)
        }
    }
}