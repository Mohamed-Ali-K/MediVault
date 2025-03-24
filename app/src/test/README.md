# MediVault Testing Guide

This document outlines the testing approach for the MediVault application, including unit tests, integration tests, and UI tests.

## Testing Layers

Our tests are structured in accordance with the application architecture:

1. **Unit Tests**: Test individual components in isolation
   - ViewModel tests
   - Repository tests
   - Utility function tests
   
2. **Integration Tests**: Test components working together
   - Repository with DAO tests
   - ViewModel with Repository tests
   
3. **UI Tests**: Test the user interface and user interactions
   - Compose UI component tests
   - Screen navigation tests
   - End-to-end tests

## Test Directories

- `app/src/test/`: Unit tests and Robolectric-based UI tests
- `app/src/androidTest/`: Instrumented tests that require an Android device/emulator

## Testing Tools and Libraries

- **JUnit 4**: Core testing framework
- **Mockito**: For mocking dependencies in unit tests
- **MockK**: Kotlin-friendly mocking library
- **Robolectric**: For testing Android components without an emulator
- **Compose Testing**: For testing Compose UI components
- **Coroutines Testing**: For testing suspending functions and flows
- **Truth**: For more readable assertions
- **Espresso**: For UI testing (used alongside Compose testing)

## Writing Tests

### Naming Convention

Tests should follow this naming pattern:

```
fun `[what is being tested] should [expected behavior]`()
```

Example: `fun `loadUserProfile should update state with user profile`()`

### Test Structure

Follow the "Arrange-Act-Assert" (AAA) pattern:

1. **Arrange**: Set up test data and dependencies
2. **Act**: Execute the code under test
3. **Assert**: Verify the results

Example:
```kotlin
@Test
fun `saveRecord calls dao insert method`() = runBlockingTest {
    // Arrange - Set up test data and mocks
    coEvery { medicalRecordDao.insertRecord(any()) } returns TEST_RECORD_ID
    
    // Act - Call the method under test
    val result = repository.saveRecord(testRecord)
    
    // Assert - Verify the results
    coVerify { medicalRecordDao.insertRecord(testRecord) }
    assertThat(result).isTrue()
}
```

### Test Utilities

We have various utility classes to help with testing:

1. `TestUtils`: Common utilities for unit tests
2. `TestData`: Test data objects and factory methods
3. `MainCoroutineRule`: Rule for testing coroutines
4. `ComposeTestUtils`: Utilities for Compose UI testing

## Running Tests

### Running Unit Tests

```bash
./gradlew test
```

### Running Specific Tests

```bash
./gradlew test --tests "com.medivault.ui.viewmodel.UserProfileViewModelTest"
```

### Running Instrumented Tests

```bash
./gradlew connectedCheck
```

### Running UI Tests with Specific Device

```bash
./gradlew connectedCheck -PdeviceId=your_device_id
```

## Test Reports

Test reports are generated at:

- Unit Tests: `app/build/reports/tests/testDebugUnitTest/index.html`
- Instrumented Tests: `app/build/reports/androidTests/connected/index.html`

## Mocking Dependencies

### Using MockK

```kotlin
// Create a mock
private val userRepository: UserRepository = mockk()

// Define behavior
coEvery { userRepository.getUserProfile(TEST_USER_ID) } returns flowOf(testUserProfile)

// Verify interactions
coVerify { userRepository.updateUserProfile(updatedProfile) }
```

### Using Mockito

```kotlin
// Create a mock
@Mock
private lateinit var userRepository: UserRepository

// Initialize mocks
@Before
fun setup() {
    MockitoAnnotations.openMocks(this)
    
    // Define behavior
    `when`(userRepository.getUserProfile(eq(TEST_USER_ID))).thenReturn(flowOf(testUserProfile))
}

// Verify interactions
verify(userRepository).updateUserProfile(eq(updatedProfile))
```

## Testing UI Components with Compose Testing

```kotlin
@Test
fun `medical record item displays correct information`() {
    // Set up the compose content
    composeTestRule.setContent {
        MedicalRecordItem(
            record = testRecord,
            onClick = {}
        )
    }

    // Verify that elements are displayed
    composeTestRule.onNodeWithText(testRecord.title).assertIsDisplayed()
    composeTestRule.onNodeWithText(testRecord.type).assertIsDisplayed()
}
```

## Best Practices

1. **Test One Thing Per Test**: Each test should verify a single concept
2. **Use Descriptive Names**: Test names should clearly describe what's being tested
3. **Isolate Tests**: Each test should be independent of others
4. **Separate Concerns**: Keep test data creation separate from test logic
5. **Minimize External Dependencies**: Use mocks for dependencies to isolate the code under test
6. **Test Error Cases**: Don't just test the happy path, also test error handling
7. **Keep Tests Fast**: Tests should execute quickly to maintain a rapid feedback loop
8. **Use Appropriate Testing Tools**: Use the right testing approach for each component
9. **Keep Tests Maintainable**: Avoid duplication by using helper methods and test utilities

## Continuous Integration

Tests are automatically run in our CI pipeline whenever changes are pushed. Failed tests will block merging.

## Coverage Goals

We aim for the following code coverage targets:
- Models and utilities: 90%+
- Repositories: 80%+
- ViewModels: 80%+
- UI Components: 70%+

Coverage reports are generated with:

```bash
./gradlew createDebugCoverageReport
```

And can be found at `app/build/reports/coverage/debug`. 