package com.medivault.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import com.medivault.data.repository.MedicalRecord
import com.medivault.data.repository.RecordType
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// Sample UI implementation for the test
@Composable
fun MedicalRecordDetailScreen(
    record: MedicalRecord?,
    onEditClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Toolbar with back button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.testTag("back_button")
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
            
            Text(
                text = "Medical Record",
                style = MaterialTheme.typography.h6
            )
            
            Row {
                IconButton(
                    onClick = onEditClick,
                    modifier = Modifier.testTag("edit_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit"
                    )
                }
                
                IconButton(
                    onClick = onDeleteClick,
                    modifier = Modifier.testTag("delete_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete"
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        if (record == null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("No record found")
            }
        } else {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                // Title
                Text(
                    text = record.title,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.testTag("record_title")
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Type
                Text(
                    text = "Type: ${record.type.name}",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.testTag("record_type")
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Date
                val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
                Text(
                    text = "Date: ${dateFormat.format(record.date)}",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.testTag("record_date")
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Doctor
                if (record.doctorName != null) {
                    Text(
                        text = "Doctor: ${record.doctorName}",
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.testTag("record_doctor")
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                }
                
                // Description
                if (record.description != null) {
                    Text(
                        text = "Description:",
                        style = MaterialTheme.typography.subtitle1
                    )
                    
                    Text(
                        text = record.description,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.testTag("record_description")
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                }
                
                // Attachments
                if (record.attachments.isNotEmpty()) {
                    Text(
                        text = "Attachments:",
                        style = MaterialTheme.typography.subtitle1
                    )
                    
                    record.attachments.forEach { attachment ->
                        Text(
                            text = attachment,
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.primary,
                            modifier = Modifier
                                .padding(vertical = 4.dp)
                                .testTag("record_attachment")
                        )
                    }
                }
            }
        }
    }
}

class MedicalRecordDetailScreenTest {
    
    @get:Rule
    val composeTestRule = createComposeRule()
    
    private val testRecord = MedicalRecord(
        id = "rec123",
        userId = "user456",
        type = RecordType.DIAGNOSIS,
        title = "Annual Checkup",
        date = Date(1672531200000), // Jan 1, 2023
        doctorName = "Dr. Smith",
        description = "Regular checkup, all clear.",
        attachments = listOf("blood_test.pdf", "xray.jpg")
    )
    
    @Before
    fun setup() {
        composeTestRule.setContent {
            MedicalRecordDetailScreen(record = testRecord)
        }
    }
    
    @Test
    fun recordDetailScreen_displaysCorrectTitle() {
        composeTestRule.onNodeWithTag("record_title")
            .assertIsDisplayed()
            .assertTextEquals("Annual Checkup")
    }
    
    @Test
    fun recordDetailScreen_displaysCorrectType() {
        composeTestRule.onNodeWithTag("record_type")
            .assertIsDisplayed()
            .assertTextEquals("Type: DIAGNOSIS")
    }
    
    @Test
    fun recordDetailScreen_displaysCorrectDoctor() {
        composeTestRule.onNodeWithTag("record_doctor")
            .assertIsDisplayed()
            .assertTextEquals("Doctor: Dr. Smith")
    }
    
    @Test
    fun recordDetailScreen_displaysCorrectDescription() {
        composeTestRule.onNodeWithTag("record_description")
            .assertIsDisplayed()
            .assertTextEquals("Regular checkup, all clear.")
    }
    
    @Test
    fun recordDetailScreen_displaysAttachments() {
        composeTestRule.onNodeWithText("Attachments:")
            .assertIsDisplayed()
        
        composeTestRule.onNodeWithText("blood_test.pdf")
            .assertIsDisplayed()
        
        composeTestRule.onNodeWithText("xray.jpg")
            .assertIsDisplayed()
    }
    
    @Test
    fun recordDetailScreen_clickEditButton_callsCallback() {
        var editClicked = false
        
        composeTestRule.setContent {
            MedicalRecordDetailScreen(
                record = testRecord,
                onEditClick = { editClicked = true }
            )
        }
        
        composeTestRule.onNodeWithTag("edit_button").performClick()
        
        assert(editClicked)
    }
    
    @Test
    fun recordDetailScreen_clickDeleteButton_callsCallback() {
        var deleteClicked = false
        
        composeTestRule.setContent {
            MedicalRecordDetailScreen(
                record = testRecord,
                onDeleteClick = { deleteClicked = true }
            )
        }
        
        composeTestRule.onNodeWithTag("delete_button").performClick()
        
        assert(deleteClicked)
    }
    
    @Test
    fun recordDetailScreen_clickBackButton_callsCallback() {
        var backClicked = false
        
        composeTestRule.setContent {
            MedicalRecordDetailScreen(
                record = testRecord,
                onBackClick = { backClicked = true }
            )
        }
        
        composeTestRule.onNodeWithTag("back_button").performClick()
        
        assert(backClicked)
    }
    
    @Test
    fun recordDetailScreen_nullRecord_showsNoRecordMessage() {
        composeTestRule.setContent {
            MedicalRecordDetailScreen(record = null)
        }
        
        composeTestRule.onNodeWithText("No record found")
            .assertIsDisplayed()
    }
} 