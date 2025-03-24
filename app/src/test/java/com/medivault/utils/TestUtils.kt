package com.medivault.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * Test utilities for MediVault app testing
 */
object TestData {
    // User test data
    const val TEST_USER_ID = "test-user-id"
    const val TEST_USER_EMAIL = "test@example.com"
    const val TEST_USER_NAME = "Test User"
    
    // Medical record test data
    const val TEST_RECORD_ID = "test-record-id"
    const val TEST_RECORD_TYPE = "Prescription"
    const val TEST_RECORD_TITLE = "Test Prescription"
    const val TEST_DOCTOR_NAME = "Dr. Smith"
    const val TEST_DESCRIPTION = "Test medical record description"
    
    // Create test timestamps
    val TEST_TIMESTAMP = System.currentTimeMillis()
    
    // Sample attachment paths
    val TEST_ATTACHMENTS = listOf("path/to/attachment1.jpg", "path/to/attachment2.pdf")
}

/**
 * Rule for testing coroutines that use Dispatchers.Main
 */
@ExperimentalCoroutinesApi
class MainCoroutineRule(
    private val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
) : TestWatcher(), TestCoroutineScope by TestCoroutineScope(testDispatcher) {
    
    override fun starting(description: Description) {
        super.starting(description)
        Dispatchers.setMain(testDispatcher)
    }
    
    override fun finished(description: Description) {
        super.finished(description)
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}

/**
 * Helper functions for testing
 */
object TestHelpers {
    /**
     * Safely compares two lists ignoring order
     */
    fun <T> assertListContentEquals(expected: List<T>, actual: List<T>) {
        assert(expected.size == actual.size) { "List sizes don't match. Expected: ${expected.size}, Actual: ${actual.size}" }
        expected.forEach { expectedItem ->
            assert(actual.contains(expectedItem)) { "Item $expectedItem not found in actual list" }
        }
    }
} 