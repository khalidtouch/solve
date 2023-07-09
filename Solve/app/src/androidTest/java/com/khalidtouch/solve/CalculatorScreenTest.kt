package com.khalidtouch.solve

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.khalidtouch.solve.ui.CalculatorViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CalculatorScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()


    @Before
    fun setup() {
        composeTestRule.setContent {
            CalculatorScreen(viewModel = CalculatorViewModel())
        }
        composeTestRule.waitForIdle()
    }


    @Test
    fun screen_hasAllComponentsShown() {
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

    @Test
    fun displayScreen_whenAdditionIsPerformed_showResult() {
        composeTestRule.onNodeWithTag("button:two").performClick()
        composeTestRule.onNodeWithTag("button:plus").performClick()
        composeTestRule.onNodeWithTag("button:three").performClick()
        composeTestRule.onNodeWithTag("button:equals").performClick()
        composeTestRule.onNodeWithText("5.0").assertIsDisplayed()
    }

    @Test
    fun displayScreen_whenSubtractionIsPerformed_showResult() {
        composeTestRule.onNodeWithTag("button:two").performClick()
        composeTestRule.onNodeWithTag("button:five").performClick()
        composeTestRule.onNodeWithTag("button:minus").performClick()
        composeTestRule.onNodeWithTag("button:five").performClick()
        composeTestRule.onNodeWithTag("button:equals").performClick()
        composeTestRule.onNodeWithText("20.0").assertIsDisplayed()
    }


    @Test
    fun displayScreen_whenDivisionIsPerformed_showResult() {
        composeTestRule.onNodeWithTag("button:two").performClick()
        composeTestRule.onNodeWithTag("button:five").performClick()
        composeTestRule.onNodeWithTag("button:divide").performClick()
        composeTestRule.onNodeWithTag("button:three").performClick()
        composeTestRule.onNodeWithTag("button:equals").performClick()
        composeTestRule.onNodeWithText("8.33", substring = true).assertIsDisplayed()
    }


    @Test
    fun displayScreen_whenMultiplicationIsPerformed_showResult() {
        composeTestRule.onNodeWithTag("button:one").performClick()
        composeTestRule.onNodeWithTag("button:zero").performClick()
        composeTestRule.onNodeWithTag("button:times").performClick()
        composeTestRule.onNodeWithTag("button:five").performClick()
        composeTestRule.onNodeWithTag("button:equals").performClick()
        composeTestRule.onNodeWithText("50.0").assertIsDisplayed()
    }

    @Test
    fun displayScreen_whenDeletePressed_performDelete() {
        composeTestRule.onNodeWithTag("button:one").performClick()
        composeTestRule.onNodeWithTag("button:two").performClick()
        composeTestRule.onNodeWithTag("button:three").performClick()
        composeTestRule.onNodeWithTag("button:del").performClick()
        composeTestRule.onNodeWithText("12").assertIsDisplayed()
    }

    @Test
    fun displayScreen_whenClearPressed_performClear() {
        composeTestRule.onNodeWithTag("button:one").performClick()
        composeTestRule.onNodeWithTag("button:two").performClick()
        composeTestRule.onNodeWithTag("button:three").performClick()
        composeTestRule.onNodeWithTag("button:ac").performClick()
        composeTestRule.onNodeWithText("123").assertDoesNotExist()

    }


}