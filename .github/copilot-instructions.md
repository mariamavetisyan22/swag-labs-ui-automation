# Swag Labs UI Automation

**ALWAYS follow these instructions first**. Only fallback to search or additional context gathering when information here is incomplete or incorrect.

Swag Labs UI Automation is a Java-based Selenium WebDriver test automation framework for testing the SauceDemo e-commerce application at https://www.saucedemo.com/. The project uses Maven for build management, TestNG for test execution, and Allure for test reporting.

## Critical Setup Requirements

### Java 21 Installation (REQUIRED)
- **CRITICAL**: This project requires Java 21. The default system Java 17 will cause build failures.
- Install Java 21: `sudo apt update && sudo apt install -y openjdk-21-jdk`
- Configure Java alternatives: `sudo update-alternatives --config java` (select Java 21 option)
- Set JAVA_HOME: `export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64`
- Verify installation: `java -version` (should show "openjdk version 21.0.x")

### Environment Setup
- Maven is pre-installed and ready to use
- Chrome (version 139+) and Firefox (version 141+) browsers are available
- All required dependencies will be downloaded automatically via Maven

## Build and Test Commands

### Core Build Process
Run all commands from the project root directory with Java 21 environment:

```bash
export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
```

### Build Commands with Timing
- **Compile**: `mvn clean compile` -- 2-4 seconds. NEVER CANCEL.
- **Test compile**: `mvn clean test-compile` -- 2-4 seconds. NEVER CANCEL.
- **Run tests**: `mvn test` -- 6-10 seconds. NEVER CANCEL.
- **Full cycle**: `mvn clean compile test` -- 6-10 seconds. NEVER CANCEL.

### Dependency Management
- **Download dependencies**: `mvn dependency:resolve` -- 10-30 seconds first time. NEVER CANCEL.
- **View dependency tree**: `mvn dependency:tree`
- **Clean build artifacts**: `mvn clean`

## Test Execution

### Browser Configuration
- Default browser: Chrome (configurable via `-Dbrowser=chrome|firefox|edge|safari`)
- Run with specific browser: `mvn test -Dbrowser=firefox`
- Browser drivers are managed automatically by Selenium Manager

### Test User Accounts
Test accounts are configured in `src/main/resources/configs/demo.properties`:
- standard_user (normal workflow testing)
- locked_out_user (locked account testing)
- problem_user (UI problem testing)
- performance_glitch_user (performance testing)
- error_user (error scenario testing)
- visual_user (visual testing)

**WARNING**: The Configuration.java file has bugs where all usernames are assigned to LOCKED_OUT_USERNAME instead of their respective variables (lines 50-53). This affects test data loading.

## Test Reporting

### Allure Reports
- **Generate report**: `mvn allure:report` -- 15-20 seconds first time (downloads Allure), 3-5 seconds subsequent runs. NEVER CANCEL.
- **Serve report**: `mvn allure:serve` -- Starts local server with live report
- **Report location**: `target/site/allure-maven-plugin/index.html`
- **Results directory**: `target/allure-results/`

### Surefire Reports
- Basic test reports: `target/surefire-reports/`
- XML format: `target/surefire-reports/TEST-*.xml`
- Text format: `target/surefire-reports/*.txt`

## Project Structure

### Source Code
```
src/
├── main/java/
│   ├── configs/Configuration.java      # Test configuration and properties
│   └── utils/BaseUtils.java           # WebDriver utility methods
├── main/resources/
│   └── configs/demo.properties        # Test data and URLs
└── test/java/
    └── tests/BaseTests.java           # Test base class (currently empty)
```

### Key Files
- `pom.xml` -- Maven configuration with Java 21, Selenium 4.31.0, TestNG 7.11.0, Allure 2.24.0
- `.gitignore` -- Excludes target/, .allure/, IDE files
- `src/main/java/configs/Configuration.java` -- Manages browser and test configuration
- `src/main/java/utils/BaseUtils.java` -- WebDriver wrapper with explicit waits (25-second timeout)

## Validation and Testing Workflow

### Manual Validation Requirements
After making changes, ALWAYS validate functionality:

1. **Build validation**: 
   ```bash
   export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
   mvn clean compile test
   ```

2. **Configuration testing**: 
   ```bash
   mvn test-compile
   # Verify Configuration.loadPropertyFile() loads demo.properties correctly
   ```

3. **Reporting validation**:
   ```bash
   mvn allure:report
   # Verify target/site/allure-maven-plugin/index.html exists
   ```

### End-to-End Scenario Testing
When implementing new tests, always validate complete user workflows:
- Login with standard_user/secret_sauce credentials
- Navigate product catalog on https://www.saucedemo.com/
- Add items to cart
- Proceed through checkout process
- Verify order completion

**Note**: Browser tests may require headless mode in CI environments. Use ChromeOptions with --headless, --no-sandbox, --disable-dev-shm-usage arguments.

### Common Validation Steps
- Verify builds complete without Java version errors
- Check configuration loading works correctly: demo.properties is read properly
- Run full test suite: `mvn clean test allure:report`
- Validate WebDriver utilities function with explicit waits
- Test in both headed and headless browser modes

## Common Commands Reference

### Build Lifecycle
```bash
# Full clean build with reporting
mvn clean compile test allure:report

# Quick compile check
mvn compile

# Test only (no compilation)
mvn surefire:test

# Clean workspace
mvn clean
```

### Troubleshooting
- **Build fails with "invalid target release: 21"**: Java 17 is active instead of Java 21
  - Solution: `export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64`
- **Browser not found**: WebDriver manager will download automatically on first run
- **Tests not found**: Ensure test classes extend appropriate base class and use TestNG annotations
- **Allure report empty**: Run tests first to generate results in `target/allure-results/`

### Performance Expectations
- Initial Maven dependency download: 30-60 seconds
- Subsequent builds: 6-10 seconds
- Allure report generation: 3-20 seconds depending on results volume
- Browser startup: 2-5 seconds per browser instance

## Development Guidelines

### Making Changes
- Always run `mvn clean compile test` after code changes
- Test with multiple browsers when changing WebDriver utilities
- Update properties file for new test data requirements
- Regenerate Allure reports after test modifications

### Code Structure
- Extend BaseTests for new test classes
- Use BaseUtils methods for WebDriver interactions (includes explicit waits)
- Store test data in demo.properties file
- Follow existing package structure (configs, utils, tests)

### Version Information
- Java: 21 (required)
- Maven: 3.9.11
- Selenium: 4.31.0
- TestNG: 7.11.0
- Allure: 2.24.0
- Target browsers: Chrome 139+, Firefox 141+

**Remember**: NEVER CANCEL long-running commands. Build processes complete in under 30 seconds, but dependency downloads may take longer on first run.

## Repository Summary

### Directory Structure
```
.
├── .github/copilot-instructions.md    # This file
├── .gitignore                         # Excludes target/, .allure/, IDE files
├── pom.xml                           # Maven build configuration
└── src/
    ├── main/java/
    │   ├── configs/Configuration.java # Configuration management
    │   └── utils/BaseUtils.java      # WebDriver utilities
    ├── main/resources/
    │   └── configs/demo.properties   # Test data and configuration
    └── test/java/
        └── tests/BaseTests.java      # Test base class
```

### Key Maven Commands Summary
```bash
# Required environment setup
export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64

# Essential commands (all under 10 seconds)
mvn clean                             # Clean workspace
mvn compile                           # Compile source code
mvn test                             # Run tests
mvn clean compile test              # Full build cycle
mvn allure:report                    # Generate test reports

# Dependency management
mvn dependency:resolve               # Download dependencies
mvn dependency:tree                  # Show dependency hierarchy

# Browser-specific testing
mvn test -Dbrowser=chrome           # Test with Chrome
mvn test -Dbrowser=firefox          # Test with Firefox
```

### Quick Start Checklist
For a fresh repository clone:
1. [ ] Install Java 21: `sudo apt install -y openjdk-21-jdk`
2. [ ] Set JAVA_HOME: `export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64`
3. [ ] Verify setup: `java -version` (should show 21.0.x)
4. [ ] Test build: `mvn clean compile test`
5. [ ] Generate report: `mvn allure:report`
6. [ ] Check output: `ls target/site/allure-maven-plugin/index.html`