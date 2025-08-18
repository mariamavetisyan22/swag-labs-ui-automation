package tests.Login;

import configs.Configuration;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import steps.LoginSteps;
import steps.ProductsPageSteps;
import tests.BaseTests;

public class LoginTests extends BaseTests {
    private LoginPage loginPage;
    private LoginSteps loginSteps;
    private ProductsPage productsPage;
    private ProductsPageSteps productsPageSteps;

    @BeforeMethod(groups = {"login"})
    public void beforeInit() {
        final WebDriver driver = getDriver();
        loginPage = new LoginPage(driver);
        loginSteps = new LoginSteps(driver);
        productsPage = new ProductsPage(driver);
        productsPageSteps = new ProductsPageSteps(driver);
    }

    @Test(description = "SLT - 001 / Verify login with standard_user", groups = {"login"})
    public void verifyLoginWIthStandardUser() {
        loginSteps.loginWithValidCredentials(Configuration.STANDARD_USERNAME, Configuration.PASSWORD);

        Assert.assertEquals(productsPage.getSectionTitle(), "Products");
    }

    @Test(description = "SLT - 002 / Verify login with locked_out_user", groups = {"login"})
    public void verifyLoginWithLockedOutUser() {
        loginSteps.loginWithValidCredentials(Configuration.LOCKED_OUT_USERNAME, Configuration.PASSWORD);

        Assert.assertEquals(loginPage.getLockedOutUserErrorMessage(), "Epic sadface: Sorry, this user has been locked out.");
    }

    @Test(description = "SLT - 003 / Verify login with problem_user", groups = {"login"})
    public void verifyLoginWithProblemUser() {
        loginSteps.loginWithValidCredentials(Configuration.PROBLEM_USERNAME, Configuration.PASSWORD);

        Assert.assertTrue(loginPage.isOnProductsPage(), "User should be redirected to products page after login");
        Assert.assertTrue(productsPageSteps.validateInvalidImages(), "There are invalid images on the Products page.");
    }

    @Test(description = "SLT - 004 / Verify login with performance_glitch_user", groups = {"login"})
    public void verifyLoginWithPerformanceGlitchUser() {
        long startTime = System.currentTimeMillis();

        loginSteps.loginWithValidCredentials(Configuration.PERFORMANCE_USERNAME, Configuration.PASSWORD);

        long endTime = System.currentTimeMillis();

        long loginDuration = endTime - startTime;

        Assert.assertTrue(loginPage.isOnProductsPage(), "User should be redirected to products page after login");
        Assert.assertEquals(productsPage.getSectionTitle(), "Products");

        Assert.assertTrue(loginDuration > 5000,
                "Performance glitch user should experience significant delay. Actual time: " + loginDuration + "ms");
    }
}
