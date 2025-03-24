package com.medivault.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.medivault.data.model.UserProfile
import com.medivault.data.repository.UserRepository
import com.medivault.utils.MainCoroutineRule
import com.medivault.utils.TestData
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.google.common.truth.Truth.assertThat

@ExperimentalCoroutinesApi
class UserProfileViewModelTest {

    // Set the main coroutines dispatcher for unit testing
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    // Mock dependencies
    private val userRepository: UserRepository = mockk()

    // Class under test
    private lateinit var viewModel: UserProfileViewModel

    // Test data
    private val testUserProfile = UserProfile(
        id = TestData.TEST_USER_ID,
        email = TestData.TEST_USER_EMAIL,
        name = TestData.TEST_USER_NAME,
        profilePicture = null,
        contactNumber = "123-456-7890",
        birthDate = TestData.TEST_TIMESTAMP,
        createdAt = TestData.TEST_TIMESTAMP
    )

    @Before
    fun setup() {
        // Set up the repository mock
        coEvery { userRepository.getUserProfile(TestData.TEST_USER_ID) } returns flowOf(testUserProfile)
        coEvery { userRepository.updateUserProfile(any()) } returns true

        // Initialize the ViewModel with mocked dependencies
        viewModel = UserProfileViewModel(userRepository)
    }

    @Test
    fun `loadUserProfile should update state with user profile`() = mainCoroutineRule.runBlockingTest {
        // When: Loading a user profile
        viewModel.loadUserProfile(TestData.TEST_USER_ID)
        
        // Then: The state should be updated with the profile
        assertThat(viewModel.uiState.value.userProfile).isEqualTo(testUserProfile)
        assertThat(viewModel.uiState.value.isLoading).isFalse()
        assertThat(viewModel.uiState.value.error).isNull()
    }

    @Test
    fun `updateUserProfile should call repository and update state`() = mainCoroutineRule.runBlockingTest {
        // Given: A user profile update
        val updatedProfile = testUserProfile.copy(name = "Updated Name")
        
        // When: Updating the profile
        viewModel.updateUserProfile(updatedProfile)
        
        // Then: Repository should be called and state updated
        coVerify { userRepository.updateUserProfile(updatedProfile) }
        assertThat(viewModel.uiState.value.isUpdating).isFalse()
        assertThat(viewModel.uiState.value.updateSuccess).isTrue()
    }

    @Test
    fun `updateUserProfile should handle error`() = mainCoroutineRule.runBlockingTest {
        // Given: Repository will return false (error)
        coEvery { userRepository.updateUserProfile(any()) } returns false
        
        // When: Updating the profile
        viewModel.updateUserProfile(testUserProfile)
        
        // Then: State should reflect the error
        assertThat(viewModel.uiState.value.updateSuccess).isFalse()
        assertThat(viewModel.uiState.value.error).isNotNull()
    }
} 