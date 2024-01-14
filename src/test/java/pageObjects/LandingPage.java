package pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import context.TestContextSetup;
import org.openqa.selenium.WebElement;
import java.util.Random;

import java.util.List;

import static enums.DataKeys.SELECTED_PRODUCT;

public class LandingPage {
    WebDriver driver;
    Logger logger;
    public LandingPage(WebDriver driver) {
        this.driver = driver;
        logger = LogManager.getLogger(LandingPage.class);
    }

    private final By logInLink = By.xpath("//a[@id=\"login2\"]");
    private final By logOutLink = By.xpath("//a[@id=\"logout2\"]");
    private final By logInButton = By.xpath("//button[text()=\"Log in\"]");
    private final By usernameInputField = By.xpath("//input[@id=\"loginusername\"]");
    private final By passwordInputField = By.xpath("//input[@id=\"loginpassword\"]");
    private final By modalDialog = By.xpath("//*[@id=\"logInModalLabel\"]");
    private final By profileButtonName = By.xpath("//*[@id=\"nameofuser\"]");
    private final By landingPageProduct = By.xpath("//*[@class=\"hrefch\"]");
    private final By shoppingCartLink = By.xpath("//a[text()=\"Cart\"]");

    public String getLandingPageTitle() {
        return driver.getTitle();
    }

    public void clickOnLogInLink() {
        driver.findElement(logInLink).click();
    }

    public void clickOnLogOutLink() {
        driver.findElement(logOutLink).click();
    }

    public void clickOnLogInButton() {
        driver.findElement(logInButton).click();
    }

    public void validateModalWindowIsDisplayed(String modalName, TestContextSetup testContextSetup) {
        switch (modalName) {
            case "Log In":
                testContextSetup.genericUtils.waitForTheElementToBeVisible(modalDialog);
                break;
            default:
                throw new RuntimeException(String.format("Please implement support for %s modal window", modalName));
        }
        logger.info(String.format("%s modal window is displayed", modalName));
        testContextSetup.genericUtils.switchToActiveElement();
    }
    public void insertUsernameAndPassword(String username, String password) {
        driver.findElement(usernameInputField).sendKeys(username);
        driver.findElement(passwordInputField).sendKeys(password);
    }

    public String getProfileButtonName(TestContextSetup testContextSetup) {
        testContextSetup.genericUtils.waitForTheElementToBeVisible(profileButtonName);
        return driver.findElement(profileButtonName).getText();
    }

    public void selectARandomProductFromLandingPage(TestContextSetup testContextSetup) {
        List<WebElement> listOfProducts =  driver.findElements(landingPageProduct);
        Random rand = new Random();
        WebElement selectedProduct =  listOfProducts.get(rand.nextInt(listOfProducts.size()));
        logger.info("Selected {} from the Landing Page", selectedProduct.getText());
        testContextSetup.save(SELECTED_PRODUCT, selectedProduct.getText());

        selectedProduct.click();
    }

    public void openShoppingCart() {
        driver.findElement(shoppingCartLink).click();
    }
}
