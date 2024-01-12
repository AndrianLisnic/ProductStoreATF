package pageObjects;

import context.TestContextSetup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static enums.Alerts.PRODUCT_ADDED;
import static enums.DataKeys.PRODUCT_PRICE;

public class ProductPage {
    WebDriver driver;
    Logger logger;
    public ProductPage(WebDriver driver) {
        this.driver = driver;
        logger = LogManager.getLogger(ProductPage.class);
    }

    private final By addToCartButton = By.xpath("//a[text()=\"Add to cart\"]");
    private final By productPrice = By.className("price-container");

    public void clickOnAddToCardButton(TestContextSetup testContextSetup) {
        testContextSetup.genericUtils.waitForTheElementToBeVisible(addToCartButton);
        driver.findElement(addToCartButton).click();
    }

    public void closePopUpConfirmationMessage(TestContextSetup testContextSetup) {
        testContextSetup.genericUtils.waitForTheAlertToBeVisible();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), PRODUCT_ADDED.getContent(), "The confirmation alert matches");
        alert.accept();
    }

    public void saveProductPriceInContext(TestContextSetup testContextSetup) {
        String extractedPrice = driver.findElement(productPrice).getText();
        String priceValue = extractedPrice.substring((extractedPrice.indexOf("$") + 1), extractedPrice.indexOf(" "));
        testContextSetup.save(PRODUCT_PRICE, priceValue);
    }
}
