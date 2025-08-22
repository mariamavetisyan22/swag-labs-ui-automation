# Swag Labs UI Automation

A comprehensive UI automation test suite for the [Swag Labs demo website](https://www.saucedemo.com/) built with Selenium WebDriver, Java, and TestNG.

## Table of Contents

- [Project Overview](#project-overview)
- [Prerequisites](#prerequisites)
- [Setup](#setup)
- [Project Structure](#project-structure)
- [Configuration](#configuration)
- [Running Tests](#running-tests)
- [Reporting](#reporting)
- [Contributing](#contributing)

## Project Overview

This project provides automated testing capabilities for the Swag Labs e-commerce demo application. The test framework is built using:

- **Java 17+** - Programming language
- **Selenium WebDriver 4.31.0** - Browser automation
- **TestNG 7.11.0** - Test framework
- **Maven** - Build and dependency management
- **Allure 2.24.0** - Test reporting
- **Lombok** - Reducing boilerplate code

## Prerequisites

Before running the tests, ensure you have the following installed:

- **Java Development Kit (JDK) 17 or higher**
- **Apache Maven 3.6+**
- **Web browsers** (Chrome, Firefox, Edge, or Safari)
- **Git** (for cloning the repository)

### Verify Installation

```bash
java -version
mvn -version
```

## Setup

1. **Clone the repository:**
   ```bash
   git clone https://github.com/mariamavetisyan22/swag-labs-ui-automation.git
   cd swag-labs-ui-automation
   ```

2. **Install dependencies:**
   ```bash
   mvn clean install
   ```

3. **Verify setup:**
   ```bash
   mvn clean compile
   ```

## Project Structure

```
swag-labs-ui-automation/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── configs/
│   │   │   │   └── Configuration.java      # Test configuration management
│   │   │   └── utils/
│   │   │       └── BaseUtils.java          # Common Selenium utilities
│   │   └── resources/
│   │       └── configs/
│   │           └── demo.properties         # Test data and configuration
│   └── test/
│       └── java/
│           └── tests/
│               └── BaseTests.java          # Base test class
├── pom.xml                                 # Maven configuration
├── .gitignore                             # Git ignore rules
└── README.md                              # This file
```

### Key Components

- **Configuration.java**: Manages browser settings, environment properties, and test data loading
- **BaseUtils.java**: Provides reusable Selenium methods for common actions (click, sendText, getText)
- **demo.properties**: Contains test usernames, passwords, and application URLs
- **BaseTests.java**: Foundation class for all test cases

## Configuration

### Browser Configuration

The framework supports multiple browsers. Set the browser using system properties:

```bash
# Chrome (default)
mvn test

# Firefox
mvn test -Dbrowser=firefox

# Edge
mvn test -Dbrowser=edge

# Safari
mvn test -Dbrowser=safari
```

### Test Data

Test credentials and URLs are configured in `src/main/resources/configs/demo.properties`:

```properties
standard.username=standard_user
locked.out.username=locked_out_user
problem.username=problem_user
performance.username=performance_glitch_user
error.username=error_user
visual.username=visual_user

demo.url=https://www.saucedemo.com/
```

### Environment Variables

| Property | Description | Default |
|----------|-------------|---------|
| `browser` | Browser to use for testing | `chrome` |

## Running Tests

### Basic Test Execution

```bash
# Run all tests
mvn test

# Run tests with specific browser
mvn test -Dbrowser=firefox

# Run with Maven Surefire plugin
mvn surefire:test
```

### Test Compilation

```bash
# Compile main and test sources
mvn compile test-compile

# Clean and compile
mvn clean compile
```

## Reporting

The project uses Allure for comprehensive test reporting.

### Generate Allure Reports

```bash
# Run tests and generate Allure results
mvn test

# Generate and serve Allure report
mvn allure:serve

# Generate static Allure report
mvn allure:report
```

### Accessing Reports

- **Allure results**: `target/allure-results/`
- **Allure reports**: `target/site/allure-maven-plugin/`

## Available Test Users

The Swag Labs demo provides different user types for testing various scenarios:

| Username | Behavior |
|----------|----------|
| `standard_user` | Standard user with normal behavior |
| `locked_out_user` | User that gets locked out |
| `problem_user` | User with problematic behavior |
| `performance_glitch_user` | User with performance issues |
| `error_user` | User that encounters errors |
| `visual_user` | User for visual testing |

**Password**: `secret_sauce` (for all users)

## Development

### Adding New Tests

1. Create test classes in `src/test/java/tests/`
2. Extend `BaseTests` class
3. Use `BaseUtils` for common Selenium operations
4. Add test data to `demo.properties` if needed

### Utility Methods

The `BaseUtils` class provides:
- `click(WebElement element)` - Safe click with wait
- `sendText(WebElement element, String text)` - Text input with wait
- `getText(WebElement element)` - Get text with wait

All methods include implicit waits and element visibility checks.

## Contributing

1. Fork the repository
2. Create a feature branch: `git checkout -b feature-name`
3. Make your changes and add tests
4. Commit your changes: `git commit -m 'Add feature'`
5. Push to the branch: `git push origin feature-name`
6. Submit a pull request

## License

This project is for educational and demonstration purposes.

## Support

For questions or issues, please create an issue in the GitHub repository.