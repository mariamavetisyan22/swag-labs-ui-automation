package pages;

import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.BaseUtils;

import java.util.List;

public class ProductsPage {
    private BaseUtils baseUtils;
    private WebDriver driver;

    @FindBy (className = "title")
    private WebElement sectionTitle;

    @FindBy (className = "inventory_list")
    private WebElement inventoryContainer;

    @FindBy (className = "inventory_item_img")
    private List<WebElement> productImages;

    public ProductsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        baseUtils = new BaseUtils(driver);
    }

    public String getSectionTitle() {
        return baseUtils.getText(sectionTitle);
    }

    @SneakyThrows
    private void verifyInventoryPageLoaded(){
        baseUtils.wait(inventoryContainer);
    }
}
