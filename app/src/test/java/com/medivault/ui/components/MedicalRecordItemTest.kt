package com.medivault.ui.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.medivault.data.model.MedicalRecord
import com.medivault.utils.TestData
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [30]) // Specifying an SDK version that Robolectric supports
class MedicalRecordItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    // Test data
    private val testRecord = MedicalRecord(
        id = TestData.TEST_RECORD_ID,
        userId = TestData.TEST_USER_ID,
        type = TestData.TEST_RECORD_TYPE,
        title = TestData.TEST_RECORD_TITLE,
        date = Date(TestData.TEST_TIMESTAMP),
        doctorName = TestData.TEST_DOCTOR_NAME,
        description = TestData.TEST_DESCRIPTION,
        attachments = TestData.TEST_ATTACHMENTS
    )

    // Format date for display
    private val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
    private val formattedDate = dateFormat.format(testRecord.date)

    @Test
    fun `medical record item displays correct information`() {
        // Set up the compose content
        composeTestRule.setContent {
            MedicalRecordItem(
                record = testRecord,
                onClick = {}
            )
        }

        // Verify that all expected elements are displayed
        composeTestRule.onNodeWithText(testRecord.title).assertIsDisplayed()
        composeTestRule.onNodeWithText(testRecord.type).assertIsDisplayed()
        composeTestRule.onNodeWithText(formattedDate).assertIsDisplayed()
        composeTestRule.onNodeWithText(testRecord.doctorName ?: "").assertIsDisplayed()
    }

    @Test
    fun `clicking on medical record item triggers onClick callback`() {
        // Track if onClick was called
        var wasClicked = false

        // Set up the compose content
        composeTestRule.setContent {
            MedicalRecordItem(
                record = testRecord,
                onClick = { wasClicked = true }
            )
        }

        // Click on the item
        composeTestRule.onNodeWithText(testRecord.title).performClick()

        // Verify that onClick was called
        assert(wasClicked) { "onClick was not called when medical record item was clicked" }
    }

    @Test
    fun `medical record item handles null doctor name`() {
        // Create a record with null doctor name
        val recordWithNullDoctor = testRecord.copy(doctorName = null)

        // Set up the compose content
        composeTestRule.setContent {
            MedicalRecordItem(
                record = recordWithNullDoctor,
                onClick = {}
            )
        }

        // Verify that all expected elements are displayed and doctor name is not
        composeTestRule.onNodeWithText(recordWithNullDoctor.title).assertIsDisplayed()
        composeTestRule.onNodeWithText("No doctor specified").assertIsDisplayed()
    }
} 