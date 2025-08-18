package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class ProductsPageSteps {
    private WebDriver driver;

    public ProductsPageSteps(final WebDriver driver) {
        this.driver = driver;
    }

    public boolean validateInvalidImages() {
        List<WebElement> images = driver.findElements(By.className("inventory_item_img"));

        for (WebElement img : images) {
            String src = img.getAttribute("src");

            if (src != null && (src.contains("WithGarbageOnItToBreakTheUrl") ||
                    src.contains("sl-404") ||
                    src.isEmpty())) {
                return true;
            }

            Boolean imageLoaded = (Boolean) ((JavascriptExecutor) driver)
                    .executeScript("return arguments[0].complete && arguments[0].naturalWidth > 0;", img);

            if (Boolean.FALSE.equals(imageLoaded)) {
                return true;
            }
        }
        return false;
    }
}
