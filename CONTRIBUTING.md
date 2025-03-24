# Contributing to MediVault

Thank you for your interest in contributing to MediVault! This document provides guidelines and instructions for contributing to this project.

## Code of Conduct

Please read and follow our [Code of Conduct](CODE_OF_CONDUCT.md). By participating, you are expected to uphold this code.

## How Can I Contribute?

### Reporting Bugs

- Use the GitHub issue tracker to report bugs
- Check if the bug has already been reported before creating a new issue
- Use the bug report template when creating an issue
- Include detailed steps to reproduce the bug
- Provide as much context as possible (device, OS version, etc.)

### Suggesting Features

- Use the GitHub issue tracker to suggest features
- Check if the feature has already been suggested
- Use the feature request template
- Provide a clear rationale for why the feature should be added
- Consider how the feature aligns with the project's goals

### Pull Requests

1. **Fork the repository**
2. **Create a branch for your feature or bugfix**
   ```
   git checkout -b feature/your-feature-name
   ```
   or
   ```
   git checkout -b fix/bug-description
   ```
3. **Make your changes**
   - Follow the coding style guidelines
   - Add tests for new features
   - Ensure all tests pass
4. **Commit your changes**
   - Use clear commit messages
   - Reference issues if applicable
5. **Push to your fork**
6. **Submit a pull request**
   - Provide a clear description of the changes
   - Link any relevant issues

## Development Setup

1. **Prerequisites**
   - Android Studio Arctic Fox or newer
   - JDK 17
   - Android SDK API 34

2. **Local Setup**
   ```
   git clone https://github.com/yourusername/medical-recored-app.git
   cd medical-recored-app
   ```

3. **Build and Test**
   ```
   ./gradlew build
   ./gradlew test
   ```

## Coding Guidelines

### Kotlin Style

- Follow the [Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html)
- Use meaningful variable names
- Keep functions small and focused
- Comment complex logic

### Architecture

- Follow MVVM architecture pattern
- Keep UI logic in ViewModels
- Use Use Cases for business logic
- Keep repositories focused on data operations

### Testing

- Write unit tests for all new features
- Aim for high test coverage
- Test edge cases
- Follow the existing test structure

## Git Workflow

### Branch Naming

- `feature/feature-name` for new features
- `bugfix/bug-description` for bug fixes
- `docs/what-changed` for documentation
- `refactor/what-changed` for code refactoring

### Commit Messages

- Use clear, descriptive commit messages
- Structure messages as: `type(scope): message`
  - Example: `feat(records): add filtering capability`
- Types:
  - `feat`: A new feature
  - `fix`: A bug fix
  - `docs`: Documentation changes
  - `style`: Formatting changes
  - `refactor`: Code refactoring
  - `test`: Adding or fixing tests
  - `chore`: Maintenance tasks

## Review Process

1. All pull requests need at least one code review before merging
2. CI checks must pass
3. All feedback must be addressed before merging
4. Changes may require additional reviews after updates

## Recognition

Contributors will be recognized in the project's README and in release notes.

## Questions?

If you have any questions, feel free to:
- Open an issue
- Contact the maintainers
- Join our community discussions on GitHub

Thank you for contributing to MediVault! 