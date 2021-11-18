package com.example.compose.rally

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.compose.rally.ui.overview.OverviewBody
import org.junit.Rule
import org.junit.Test

class OverviewScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun overviewScreen_alertsDisplayed() {
        composeTestRule.setContent {
            OverviewBody()
        }

        // Any test that you write must be properly synchronized with the subject under test. 
        // when you use a finder such as onNodeWithText, the test waits until the app is idle before querying the semantics tree.
        // Without synchronization, tests could look for elements before they're displayed or they could wait unnecessarily.
        composeTestRule
            .onNodeWithText("Alerts")
            .assertIsDisplayed()
    }
}
