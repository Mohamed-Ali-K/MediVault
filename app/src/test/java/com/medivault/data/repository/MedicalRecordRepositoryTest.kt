package com.medivault.data.repository

import com.google.common.truth.Truth.assertThat
import com.medivault.data.dao.MedicalRecordDao
import com.medivault.data.model.MedicalRecord
import com.medivault.utils.MainCoroutineRule
import com.medivault.utils.TestData
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.Date

@ExperimentalCoroutinesApi
class MedicalRecordRepositoryTest {

    // Set the main coroutines dispatcher for unit testing
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    // Mock dependencies
    private val medicalRecordDao: MedicalRecordDao = mockk()

    // Class under test
    private lateinit var repository: MedicalRecordRepository

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

    @Before
    fun setup() {
        // Initialize the repository with mocked dependencies
        repository = MedicalRecordRepositoryImpl(medicalRecordDao)
    }

    @Test
    fun `getRecordById returns record from dao`() = mainCoroutineRule.runBlockingTest {
        // Given: DAO returns a record
        coEvery { medicalRecordDao.getRecordById(TestData.TEST_RECORD_ID) } returns testRecord
        
        // When: Getting a record by ID
        val result = repository.getRecordById(TestData.TEST_RECORD_ID)
        
        // Then: The returned record should match
        assertThat(result).isEqualTo(testRecord)
    }

    @Test
    fun `getRecordsByUserId returns records from dao`() = mainCoroutineRule.runBlockingTest {
        // Given: DAO returns a flow of records
        val records = listOf(testRecord)
        coEvery { medicalRecordDao.getRecordsByUserId(TestData.TEST_USER_ID) } returns flowOf(records)
        
        // When: Getting records by user ID
        val result = repository.getRecordsByUserId(TestData.TEST_USER_ID).first()
        
        // Then: The returned records should match
        assertThat(result).isEqualTo(records)
    }

    @Test
    fun `saveRecord calls dao insert method`() = mainCoroutineRule.runBlockingTest {
        // Given: DAO insert returns ID
        coEvery { medicalRecordDao.insertRecord(any()) } returns TestData.TEST_RECORD_ID
        
        // When: Saving a record
        val result = repository.saveRecord(testRecord)
        
        // Then: DAO insert should be called and result should be true
        coVerify { medicalRecordDao.insertRecord(testRecord) }
        assertThat(result).isTrue()
    }

    @Test
    fun `saveRecord returns false when dao throws exception`() = mainCoroutineRule.runBlockingTest {
        // Given: DAO throws exception
        coEvery { medicalRecordDao.insertRecord(any()) } throws RuntimeException("Database error")
        
        // When: Saving a record
        val result = repository.saveRecord(testRecord)
        
        // Then: Result should be false
        assertThat(result).isFalse()
    }

    @Test
    fun `deleteRecord calls dao delete method`() = mainCoroutineRule.runBlockingTest {
        // Given: DAO delete returns success
        coEvery { medicalRecordDao.deleteRecord(any()) } returns 1
        
        // When: Deleting a record
        val result = repository.deleteRecord(testRecord)
        
        // Then: DAO delete should be called and result should be true
        coVerify { medicalRecordDao.deleteRecord(testRecord) }
        assertThat(result).isTrue()
    }

    @Test
    fun `getRecordsByType returns filtered records from dao`() = mainCoroutineRule.runBlockingTest {
        // Given: DAO returns a flow of records
        val records = listOf(testRecord)
        coEvery { 
            medicalRecordDao.getRecordsByUserIdAndType(TestData.TEST_USER_ID, TestData.TEST_RECORD_TYPE) 
        } returns flowOf(records)
        
        // When: Getting records by type
        val result = repository.getRecordsByType(
            userId = TestData.TEST_USER_ID, 
            type = TestData.TEST_RECORD_TYPE
        ).first()
        
        // Then: The returned records should match
        assertThat(result).isEqualTo(records)
    }
} 