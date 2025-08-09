package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BaseUtils;

public class LoginPage {
    private BaseUtils baseUtils;
    private WebDriver driver;

    @FindBy(id = "user-name")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(className = "error-message-container")
    private WebElement lockedOutUserErrorMessage;

    public LoginPage(final WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        baseUtils = new BaseUtils(driver);
    }

    public LoginPage enterUsername(final String user) {
        baseUtils.sendText(username, user);
        return this;
    }

    public LoginPage enterPassword(final String pass) {
        baseUtils.sendText(password, pass);
        return this;
    }

    public LoginPage clickLoginButton() {
        baseUtils.click(loginButton);
        return this;
    }

    public String getLockedOutUserErrorMessage() {
        return baseUtils.getText(lockedOutUserErrorMessage);
    }
}
