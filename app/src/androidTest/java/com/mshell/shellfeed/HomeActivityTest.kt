package com.mshell.shellfeed

import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mshell.shellfeed.ui.HomeActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {
    @get:Rule
    val activityRule = createAndroidComposeRule<HomeActivity>()

    @Test
    fun isDisplayed() {
//        activityRule
//            .
    }
//    @Test
//    fun clickNewsItem() {
//        activityRule
//            .onAllNodes(hasClickAction())
//            .onFirst()
//            .assertExists()
//            .performClick()
//    }
}