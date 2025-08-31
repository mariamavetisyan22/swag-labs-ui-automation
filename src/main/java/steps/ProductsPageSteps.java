package steps;

import org.openqa.selenium.*;
import utils.BaseUtils;

import java.util.List;

public class ProductsPageSteps {
    private WebDriver driver;
    private BaseUtils baseUtils;

    public ProductsPageSteps(final WebDriver driver) {
        this.driver = driver;
        baseUtils = new BaseUtils(driver);
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

    public boolean validateProductInteractionErrors() {
        try {
            List<WebElement> addToCartButtons = driver.findElements(By.xpath("//button[contains(@id,'add-to-cart')]"));
            if (!addToCartButtons.isEmpty()) {
                addToCartButtons.get(0).click();
                final WebElement errorElement = driver.findElement(By.xpath("//h3[@data-test='error' or contains(@class,'error')]"));

                try {
                    baseUtils.waitForPresence(errorElement);
                    return true;
                } catch (TimeoutException e) {
                    String buttonText = baseUtils.getText(addToCartButtons.get(0));
                    return !buttonText.toLowerCase().contains("remove");
                }
            }
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    public boolean validateVisualAnomalies() {
        try {
            List<WebElement> productImages = driver.findElements(By.className("inventory_item_img"));

            for (WebElement img : productImages) {
                String src = img.getAttribute("src");

                if (src != null && src.contains("jpg")) {
                    String width = img.getCssValue("width");
                    String height = img.getCssValue("height");

                    if (!width.equals("160px") || !height.equals("120px")) {
                        return true;
                    }
                }
            }

            List<WebElement> inventoryItems = driver.findElements(By.className("inventory_item"));
            for (WebElement item : inventoryItems) {
                String transform = item.getCssValue("transform");

                if (!transform.equals("none")) {
                    return true;
                }
            }

            List<WebElement> productNames = driver.findElements(By.className("inventory_item_name"));
            for (WebElement name : productNames) {
                String textAlign = name.getCssValue("text-align");

                if (textAlign.equals("right")) {
                    return true;
                }
            }

            return false;
        } catch (Exception e) {
            return true;
        }
    }
}
