# MediVault

MediVault is a secure medical records application designed to help users manage their health information securely on their mobile devices without requiring internet access.

## Project Overview

### Vision
To create a user-friendly, secure, and comprehensive medical records application that empowers individuals to take control of their healthcare information.

### Key Features
- Personal medical records management
- Secure document storage
- User profiles for multiple family members
- Medication tracking
- Appointment scheduling
- Health metrics monitoring

## Architecture

MediVault follows a clean architecture approach with MVVM pattern:

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│  Presentation   │    │     Domain      │    │      Data       │
│    Layer        │◄───┤     Layer       │◄───┤     Layer       │
│ (UI/ViewModels) │    │  (Use Cases)    │    │ (Repositories)  │
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

### Core Components
- **Room Database**: For local encrypted storage of medical records
- **Compose UI**: Modern declarative UI toolkit
- **ViewModel**: For UI state management and business logic

## Getting Started

### Prerequisites
- Android Studio (latest version recommended)
- Java Development Kit (JDK) 17
- Gradle 8.x
- Android SDK (API level 34)

### Environment Setup
1. Set up Android environment variables:
   ```
   set ANDROID_HOME=C:\Users\kenis\AppData\Local\Android\Sdk
   set PATH=%PATH%;%ANDROID_HOME%\platform-tools
   ```

2. Verify environment setup:
   ```
   gradlew.bat -v
   adb version
   ```

### Building the Project
Clone the repository and open in Android Studio:
```
git clone https://github.com/yourusername/medical-recored-app.git
cd medical-recored-app
```

## Testing

### Running Tests
To run unit tests, execute:
```
.\gradlew.bat :app:testDebugUnitTest
```

Test results can be found at:
```
app/build/reports/tests/testDebugUnitTest/index.html
```

### Key Test Components
- `SimpleTest`: Basic unit tests for non-Android functionality
- `RobolectricSimpleTest`: Tests that require Android framework using Robolectric
- `StringUtilsTest`: Tests for string utility functions

## Contribution Guidelines

### Code Style
- Follow Kotlin coding conventions
- Use meaningful variable and function names
- Write comments for complex logic
- Include documentation for public APIs

### Pull Request Process
1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

### Commit Guidelines
- Use clear, descriptive commit messages
- Reference issue numbers when applicable
- Keep commits focused on single responsibilities

## Security Considerations

- All data is stored locally on the device
- Database encryption using SQLCipher
- No internet connectivity required
- User authentication required for access

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Features

- Secure storage for medical records
- Categorize records by type (diagnoses, lab results, prescriptions, etc.)
- User authentication and data encryption
- Attach files to medical records
- Search and filter functionality

## Project Setup

### Prerequisites

- Android Studio Flamingo (2023.2.1) or newer
- JDK 17
- Android SDK 33 (minimum SDK 24)

### Building the Project

1. Clone the repository:
```bash
git clone https://github.com/yourusername/MediVault.git
```

2. Open the project in Android Studio.

3. Sync Gradle and build the project.

## Development with Cursor AI

This project is configured to work optimally with Cursor AI. The following files enhance your development experience:

- `.cursor.json`: Configuration for Cursor AI features including code assistance, analysis, and formatting
- `.cursorignore`: Specifies which files should be ignored by Cursor AI
- `.editorconfig`: Ensures consistent coding styles across different editors

### Cursor AI Features

When working with this project in Cursor AI, you'll benefit from:

- Intelligent code completion
- Error detection and quick fixes
- Automated formatting
- Enhanced documentation lookup
- Context-aware suggestions
- Code navigation assistance

## Project Governance

This project follows structured governance processes to ensure quality and maintainability:

### Issue Management

- Use the provided issue templates for bug reports and feature requests
- All issues are tracked in the GitHub Issues tab
- Label issues appropriately for categorization

### Pull Requests

- Follow the pull request template when submitting changes
- All code changes require review before merging
- CI checks must pass before merging

### Code Ownership

- The CODEOWNERS file defines who is responsible for various parts of the codebase
- Code owners are automatically requested for review on relevant PRs

## Testing

MediVault has comprehensive testing implemented at various levels:

### Running Unit Tests

Unit tests verify individual components in isolation and can be run on your local machine without an emulator.

```bash
./gradlew test
```

This will execute all unit tests and generate a report in `app/build/reports/tests/`.

### Running Instrumentation Tests

Instrumentation tests require an Android device or emulator to run.

```bash
./gradlew connectedCheck
```

This will execute all instrumentation tests and generate a report in `app/build/reports/androidTests/`.

### Testing Components

#### Repository Tests

Repository tests ensure that data operations work correctly:

- `UserRepositoryTest`: Tests for user data management
- `MedicalRecordRepositoryTest`: Tests for medical record CRUD operations

#### Database Tests

Database tests verify Room database implementation:

- `MediVaultDatabaseTest`: Tests Room entities, DAOs, and queries

#### ViewModel Tests

ViewModel tests validate business logic and state management:

- `MainViewModelTest`: Tests for app startup logic
- `MedicalRecordViewModelTest`: Tests for medical record display and manipulation

#### UI Tests

UI tests ensure that the user interface behaves as expected:

- `MedicalRecordDetailScreenTest`: Tests for the record detail screen

### Continuous Integration

This project uses GitHub Actions for continuous integration. Every push or pull request triggers:

1. Unit tests
2. Build verification
3. Instrumentation tests on an emulator
4. Test report generation

The workflow configuration is in `.github/workflows/android-ci.yml`.

## Project Structure

The project follows a clean architecture approach with the following structure:

```
app/
├── src/
│   ├── main/
│   │   ├── java/com/medivault/
│   │   │   ├── data/        # Data layer: repositories, data sources
│   │   │   ├── di/          # Dependency injection
│   │   │   ├── domain/      # Domain layer: use cases, domain models
│   │   │   ├── ui/          # UI layer: screens, components
│   │   │   │   └── theme/   # UI theme and styling
│   │   │   └── util/        # Utility classes
│   │   └── res/             # Android resources
│   ├── test/                # Unit tests
│   └── androidTest/         # Instrumentation tests
```

## Best Practices

When contributing to this project, follow these development best practices:

1. Follow the MVVM architecture pattern
2. Write tests for new features before implementation (TDD)
3. Use Kotlin coroutines for asynchronous operations
4. Keep UI components stateless and driven by ViewModels
5. Adhere to Material Design guidelines
6. Use dependency injection with Hilt

## License

[Insert License Information]

## Testing Instructions

### Prerequisites
- Android Studio (latest version recommended)
- Java Development Kit (JDK) 17
- Gradle 8.x
- Android SDK (API level 34)

### Environment Setup
1. Set up Android environment variables:
   ```
   set ANDROID_HOME=C:\Users\kenis\AppData\Local\Android\Sdk
   set PATH=%PATH%;%ANDROID_HOME%\platform-tools
   ```

2. Verify environment setup:
   ```
   gradlew.bat -v
   adb version
   ```

### Running Tests
To run unit tests, execute:
```
.\gradlew.bat :app:testDebugUnitTest
```

This will run all the unit tests in the project. The test results can be found in:
```
app/build/reports/tests/testDebugUnitTest/index.html
```

## Project Structure

### Key Components
- `SimpleTest`: Basic unit tests for non-Android functionality
- `RobolectricSimpleTest`: Tests that require Android framework using Robolectric
- `StringUtilsTest`: Tests for string utility functions

### Dependencies
- JUnit: For basic unit testing
- Robolectric: For testing Android components without emulator
- Mockito: For mocking dependencies in tests

## Notes
- The project uses AndroidX and Jetpack Compose for the UI
- The application follows MVVM architecture
- Room database is used for local storage 