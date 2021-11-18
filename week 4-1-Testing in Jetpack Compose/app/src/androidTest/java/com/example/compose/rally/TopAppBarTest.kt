package com.example.compose.rally

import androidx.compose.material.Text
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasParent
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import com.example.compose.rally.ui.components.RallyTopAppBar
import org.junit.Rule
import org.junit.Test

class TopAppBarTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun myTest() {
        composeTestRule.setContent {
            Text("You can set any Compose content!")
        }
    }

    @Test
    fun rallyTopAppBarTest() {
        val allScreens = RallyScreen.values()
            .toList()
        composeTestRule.setContent {
            RallyTopAppBar(
                allScreens = allScreens,
                onTabSelected = { },
                currentScreen = RallyScreen.Accounts
            )
        }
        Thread.sleep(5000)
    }

    @Test
    fun rallyTopAppBarTest_currentTabSelected() {
        val allScreens = RallyScreen.values()
            .toList()
        composeTestRule.setContent {
            RallyTopAppBar(
                allScreens = allScreens,
                onTabSelected = { },
                currentScreen = RallyScreen.Accounts
            )
        }

        // composeTestRule{.finder}{.assertion}{.action}
        // https://developer.android.com/jetpack/compose/testing-cheatsheet?authuser=4

        // Debugging tests
        composeTestRule.onRoot()
            .printToLog("currentLabelExists")
        composeTestRule
//            .onNodeWithText(RallyScreen.Accounts.name.uppercase())
            .onNodeWithContentDescription(RallyScreen.Accounts.name)
            .assertExists()

        // The property MergeDescendants = 'true' is telling us that this node had descendants, but they have been merged into it.
        // In tests we oftentimes need to access all nodes.

        // In order to verify whether the Text inside the tab is displayed or not
        composeTestRule.onRoot(useUnmergedTree = true)
            .printToLog("currentLabelExists")

        composeTestRule
            .onNode(
                hasText(RallyScreen.Accounts.name.uppercase()) and
                    hasParent(hasContentDescription(RallyScreen.Accounts.name)),
                useUnmergedTree = true
            )
            .assertExists()
    }
}
