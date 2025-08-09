package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BaseUtils;

public class ProductsPage {
    private BaseUtils baseUtils;
    private WebDriver driver;

    @FindBy (className = "title")
    private WebElement sectionTitle;

    public ProductsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        baseUtils = new BaseUtils(driver);
    }

    public String getSectionTitle() {
        return baseUtils.getText(sectionTitle);
    }
}
