package tests.Login;

import configs.Configuration;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import tests.BaseTests;

public class LoginTests extends BaseTests {
    private LoginPage loginPage;
    private ProductsPage productsPage;

    @BeforeMethod(groups = {"login"})
    public void beforeInit() {
        final WebDriver driver = getDriver();
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
    }

    @Test(description = "SLT - 001 / Verify login with standard_user", groups = {"login"})
    public void verifyLoginWIthStandardUser() {
        loginPage.enterUsername(Configuration.STANDARD_USERNAME)
                .enterPassword(Configuration.PASSWORD)
                .clickLoginButton();
        Assert.assertEquals(productsPage.getSectionTitle(), "Products");
    }

    @Test(description = "SLT - 002 / Verify login with locked_out_user", groups = {"login"})
    public void verifyLoginWithLockedOutUser() {
        loginPage.enterUsername(Configuration.LOCKED_OUT_USERNAME)
                .enterPassword(Configuration.PASSWORD)
                .clickLoginButton();
        Assert.assertEquals(loginPage.getLockedOutUserErrorMessage(), "Epic sadface: Sorry, this user has been locked out.");
    }
}
