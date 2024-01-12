package pageObjects;

import context.TestContextSetup;
import dataTable.OrderDetails;
import io.cucumber.java.en.And;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static enums.DataKeys.PRODUCT_PRICE;
import static enums.DataKeys.SELECTED_PRODUCT;


public class CartPage {
    WebDriver driver;
    Logger logger;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        logger = LogManager.getLogger(CartPage.class);
    }

    private final By productsTable = By.xpath(" //*[@class=\"success\"]");
    private final By productName = By.xpath("//table[@class=\"table table-bordered table-hover table-striped\"]//td[2]");
    private final By productPrice = By.xpath("//table[@class=\"table table-bordered table-hover table-striped\"]//td[3]");
    private final By placeOrderButton = By.xpath("//button[text()=\"Place Order\"]");
    private final By modalDialog = By.xpath("//*[@id=\"orderModalLabel\"]");
    private final By nameInputField = By.xpath("//*[@id=\"name\"]\t");
    private final By countryInputField = By.xpath("//*[@id=\"country\"]\t");
    private final By cityInputField = By.xpath("//*[@id=\"city\"]\t");
    private final By creditCardInputField = By.xpath("//*[@id=\"card\"]\t");
    private final By monthInputField = By.xpath("//*[@id=\"month\"]\t");
    private final By yearInputField = By.xpath("//*[@id=\"year\"]\t");
    private final By purchaseButton = By.xpath("//button[text()=\"Purchase\"]");
    private final By purchaseConfirmationMessage = By.xpath("//h2[text()=\"Thank you for your purchase!\"]");
    private final By purchaseDetails = By.xpath("//*[contains(text(),'Id')]");
    private final By okConfirmationButton = By.xpath("//button[text()=\"OK\"]");

    public void validateProductNameAndPriceInCart(TestContextSetup testContextSetup) {
        testContextSetup.genericUtils.waitForTheElementToBeVisible(productsTable);

        SoftAssert softAssert = new SoftAssert();
        String name = driver.findElement(productName).getText();
        String price = driver.findElement(productPrice).getText();

        softAssert.assertEquals(name, testContextSetup.getData(SELECTED_PRODUCT), "Selected product name matches");
        softAssert.assertEquals(price, testContextSetup.getData(PRODUCT_PRICE),
                "Selected product price matches");
        softAssert.assertAll();
        logger.info("Product name <{}> and product price <{}> matches.", name, price);
    }

    public void clickOnPlaceOrderButton() {
        driver.findElement(placeOrderButton).click();
        logger.info("The user clicked on Place Order button");
    }

    public void fillInTheOrderDetails(List<OrderDetails> orderDetailsList, TestContextSetup testContextSetup) {
        testContextSetup.genericUtils.waitForTheElementToBeVisible(modalDialog);
        driver.findElement(nameInputField).sendKeys(orderDetailsList.get(0).name);
        driver.findElement(countryInputField).sendKeys(orderDetailsList.get(0).country);
        driver.findElement(cityInputField).sendKeys(orderDetailsList.get(0).city);
        driver.findElement(creditCardInputField).sendKeys(orderDetailsList.get(0).creditCard);
        driver.findElement(monthInputField).sendKeys(orderDetailsList.get(0).month);
        driver.findElement(yearInputField).sendKeys(orderDetailsList.get(0).year);
    }

    public void clickOnPurchaseButton() {
        driver.findElement(purchaseButton).click();
        logger.info("The user clicked on Purchase button");
    }

    public void validatePurchaseDetailsInConformationMessage(TestContextSetup testContextSetup) {
        testContextSetup.genericUtils.waitForTheElementToBeVisible(purchaseConfirmationMessage);

        String purchaseDetailsContent = driver.findElement(purchaseDetails).getText();
        // Find the index of the colon (:) and the space after it
        int amountIndex = purchaseDetailsContent.indexOf("Amount: ");
        String amountSubstring = purchaseDetailsContent.substring(amountIndex + 8);
        int spaceIndex = amountSubstring.indexOf(" ");
        String amount = amountSubstring.substring(0, spaceIndex);

        Assert.assertEquals(testContextSetup.getData(PRODUCT_PRICE), amount, "Purchased successfully");
        logger.info("Product purchased successfully!");
    }

    public void clickOKButtonFromTheConfirmationPopUp() {
        driver.findElement(okConfirmationButton).click();
    }
}
