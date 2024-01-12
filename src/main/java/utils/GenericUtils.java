package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GenericUtils {
    WebDriver driver;
    private final Logger logger;

    public GenericUtils(WebDriver driver) {
        this.driver = driver;
        logger = LogManager.getLogger(GenericUtils.class);
    }

    public void switchToActiveElement() {
        driver.switchTo().activeElement();
    }

    public void waitForTheElementToBeVisible(By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));

        logger.info(String.format("%s element is found on the page", element.toString()));
    }

    public void waitForTheAlertToBeVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();

        logger.info(String.format("\"%s\" alert is present on the page", alert.getText()));
    }
}
