package com.khalidtouch.solve

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CalculatorScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()



    @Before
    fun setup() {
        composeTestRule.setContent {
            CalculatorScreen()
        }
        composeTestRule.waitForIdle()
    }


    @Test
    fun screen_hasAllButtonsInCheck() {
        composeTestRule.onNodeWithTag("display:main").assertIsDisplayed()
        composeTestRule.onNodeWithTag("button:ac").assertIsDisplayed()
        composeTestRule.onNodeWithTag("button:del").assertIsDisplayed()
        composeTestRule.onNodeWithTag("button:divide").assertIsDisplayed()
        composeTestRule.onNodeWithTag("button:seven").assertIsDisplayed()
        composeTestRule.onNodeWithTag("button:eight").assertIsDisplayed()
        composeTestRule.onNodeWithTag("button:nine").assertIsDisplayed()
        composeTestRule.onNodeWithTag("button:times").assertIsDisplayed()
        composeTestRule.onNodeWithTag("button:four").assertIsDisplayed()
        composeTestRule.onNodeWithTag("button:five").assertIsDisplayed()
        composeTestRule.onNodeWithTag("button:six").assertIsDisplayed()
        composeTestRule.onNodeWithTag("button:minus").assertIsDisplayed()
        composeTestRule.onNodeWithTag("button:one").assertIsDisplayed()
        composeTestRule.onNodeWithTag("button:two").assertIsDisplayed()
        composeTestRule.onNodeWithTag("button:three").assertIsDisplayed()
        composeTestRule.onNodeWithTag("button:plus").assertIsDisplayed()
        composeTestRule.onNodeWithTag("button:zero").assertIsDisplayed()
        composeTestRule.onNodeWithTag("button:decimal").assertIsDisplayed()
        composeTestRule.onNodeWithTag("button:equals").assertIsDisplayed()
    }
}