package com.medivault.utils

import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.medivault.data.model.MedicalRecord
import com.medivault.data.model.UserProfile
import java.util.Date

/**
 * Extension functions and utilities for Compose UI testing
 */
object ComposeTestUtils {
    /**
     * Waits for a node with the given text to be displayed
     */
    fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.waitForNodeWithText(
        text: String,
        timeoutMillis: Long = 5000
    ) {
        val startTime = System.currentTimeMillis()
        while (System.currentTimeMillis() - startTime < timeoutMillis) {
            try {
                onNodeWithText(text).assertIsDisplayed()
                return
            } catch (e: AssertionError) {
                // Node not found yet, wait and retry
                Thread.sleep(100)
            }
        }
        // Last attempt after timeout
        onNodeWithText(text).assertIsDisplayed()
    }

    /**
     * Waits for a node with the given tag to be displayed
     */
    fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.waitForNodeWithTag(
        testTag: String,
        timeoutMillis: Long = 5000
    ) {
        val startTime = System.currentTimeMillis()
        while (System.currentTimeMillis() - startTime < timeoutMillis) {
            try {
                onNodeWithTag(testTag).assertIsDisplayed()
                return
            } catch (e: AssertionError) {
                // Node not found yet, wait and retry
                Thread.sleep(100)
            }
        }
        // Last attempt after timeout
        onNodeWithTag(testTag).assertIsDisplayed()
    }

    /**
     * Finds a node with text from a string resource, supporting string formatting
     */
    fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.onNodeWithStringResource(
        @StringRes id: Int,
        vararg formatArgs: Any
    ): SemanticsNodeInteraction {
        val text = activity.getString(id, *formatArgs)
        return onNodeWithText(text)
    }

    /**
     * Clicks on a node with text from a string resource
     */
    fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.clickOnStringResource(
        @StringRes id: Int,
        vararg formatArgs: Any
    ) {
        onNodeWithStringResource(id, *formatArgs).performClick()
    }

    /**
     * Types text into a node with the given test tag
     */
    fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.typeIntoNodeWithTag(
        testTag: String,
        text: String
    ) {
        onNodeWithTag(testTag).performTextInput(text)
    }

    /**
     * Checks that exactly the expected number of nodes with the given text exists
     */
    fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.assertNodeWithTextCount(
        text: String,
        expectedCount: Int
    ) {
        assert(onAllNodesWithText(text).fetchSemanticsNodes().size == expectedCount) {
            "Expected $expectedCount nodes with text '$text' but found ${onAllNodesWithText(text).fetchSemanticsNodes().size}"
        }
    }
}

/**
 * Test data for UI tests
 */
object TestData {
    // Colors for testing
    val PRIMARY_COLOR = Color(0xFF1976D2)
    val SECONDARY_COLOR = Color(0xFFE91E63)
    
    // User test data
    const val TEST_USER_ID = "test-user-id"
    const val TEST_USER_EMAIL = "test@example.com"
    const val TEST_USER_NAME = "Test User"
    const val TEST_USER_PHONE = "123-456-7890"
    
    // Medical record test data
    const val TEST_RECORD_ID = "test-record-id"
    const val TEST_RECORD_TYPE = "Prescription"
    const val TEST_RECORD_TITLE = "Test Prescription"
    const val TEST_DOCTOR_NAME = "Dr. Smith"
    const val TEST_DESCRIPTION = "Test medical record description"
    
    // Create timestamps
    val TEST_DATE = Date()
    
    // Sample attachment paths
    val TEST_ATTACHMENTS = listOf("path/to/attachment1.jpg", "path/to/attachment2.pdf")
    
    // Factory methods to create test objects
    fun createTestUserProfile(): UserProfile {
        return UserProfile(
            id = TEST_USER_ID,
            email = TEST_USER_EMAIL,
            name = TEST_USER_NAME,
            profilePicture = null,
            contactNumber = TEST_USER_PHONE,
            birthDate = TEST_DATE.time,
            createdAt = TEST_DATE.time
        )
    }
    
    fun createTestMedicalRecord(): MedicalRecord {
        return MedicalRecord(
            id = TEST_RECORD_ID,
            userId = TEST_USER_ID,
            type = TEST_RECORD_TYPE,
            title = TEST_RECORD_TITLE,
            date = TEST_DATE,
            doctorName = TEST_DOCTOR_NAME,
            description = TEST_DESCRIPTION,
            attachments = TEST_ATTACHMENTS
        )
    }
} 