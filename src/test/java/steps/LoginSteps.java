package steps;

import org.openqa.selenium.WebDriver;
import pages.LoginPage;

public class LoginSteps {
    private WebDriver driver;
    private final LoginPage loginPage;

    public LoginSteps(final WebDriver driver) {
        this.driver = driver;
        loginPage = new LoginPage(driver);
    }

    public void loginWithValidCredentials(final String username, final String password) {
        loginPage.enterUsername(username)
                .enterPassword(password)
                .clickLoginButton();
    }
}
