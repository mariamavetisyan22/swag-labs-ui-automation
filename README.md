# Swag Labs UI Automation

Swag Labs UI Automation is a Java-based Selenium WebDriver test automation framework for testing the SauceDemo e-commerce site (https://www.saucedemo.com/). The project uses Maven for build management, TestNG for test execution, and Allure for test reporting.

## Critical Setup

- Java: **Java 21 is required**. The project is compiled/targeted to Java 21.
  - Install: `sudo apt update && sudo apt install -y openjdk-21-jdk`
  - Configure alternatives: `sudo update-alternatives --config java` (select Java 21)
  - Set JAVA_HOME: `export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64`
  - Verify: `java -version` (should show 21.x)

- Maven: use your system Maven (project tested with Maven 3.9.x).

## Key Versions (from pom.xml)
- Selenium: 4.31.0
- TestNG: 7.11.0
- Allure Maven Plugin: 2.24.0
- Java source/target: 21

## Build & Test (run from project root)
Ensure JAVA_HOME points to Java 21 before running any mvn command.

Basic commands:
- Set Java env:
  export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64

- Clean:
  mvn clean

- Compile:
  mvn clean compile

- Test compile:
  mvn clean test-compile

- Run tests:
  mvn test

- Full cycle:
  mvn clean compile test

- Generate Allure report:
  mvn allure:report

- Serve Allure report:
  mvn allure:serve

## Browser configuration
- Default browser: Chrome (override with `-Dbrowser=chrome|firefox|edge|safari`).
- Example: `mvn test -Dbrowser=firefox`
- Browser drivers are managed automatically (Selenium Manager).

## Test accounts
Test credentials are defined in src/main/resources/configs/demo.properties:
- standard_user / secret_sauce
- locked_out_user
- problem_user
- performance_glitch_user
- error_user
- visual_user

## Reporting
- Allure results: `target/allure-results/`
- Allure report: `target/site/allure-maven-plugin/index.html`
- Surefire reports: `target/surefire-reports/`

## Notes & Troubleshooting
- If you see "invalid target release: 21", ensure Java 21 is active (check JAVA_HOME and java -version).
- For CI/headless environments, use ChromeOptions with `--headless --no-sandbox --disable-dev-shm-usage`.
- First-time runs may download dependencies and Allure; allow extra time.

## Fix: errors when running tests from the terminal

Common causes and quick fixes:

1. Java version mismatch
   - Ensure Java 21 is active:
     export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
     java -version   # must show 21.x

2. Maven/TestNG discovery
   - Ensure your test classes match Surefire default patterns (e.g. *Test, *Tests, *TestCase) or place a `testng.xml` at the project root and run `mvn test -Dsurefire.suiteXmlFiles=testng.xml`.
   - Example run:
     mvn clean test -Dbrowser=chrome

3. Known Configuration.java bug (affects credentials loading)
   - Symptom: tests fail due to incorrect username values (all set to locked_out_user).
   - Fix: update Configuration.java so each username variable reads its own property key instead of using the locked-out key for all. Example correction (illustrative — edit src/main/java/configs/Configuration.java):

   ```java
   // illustrative snippet to fix username assignments
   // ensure you replace the incorrect lines with the following:
   STANDARD_USERNAME = properties.getProperty("standard_user");
   LOCKED_OUT_USERNAME = properties.getProperty("locked_out_user");
   PROBLEM_USERNAME = properties.getProperty("problem_user");
   PERFORMANCE_GLITCH_USERNAME = properties.getProperty("performance_glitch_user");
   // ...other property reads...
   ```

4. Run full validation
   export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
   mvn clean compile test

5. If Allure report is needed after tests:
   mvn allure:report
   mvn allure:serve

## Project structure
- src/main/java/configs/Configuration.java
- src/main/java/utils/BaseUtils.java
- src/main/resources/configs/demo.properties
- test classes under src/test/java/

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