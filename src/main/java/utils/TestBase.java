package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.util.Properties;

public class TestBase {
    public WebDriver driver;
    public WebDriver webDriverManager() throws Exception {
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\global.properties");
        Properties properties = new Properties();
        properties.load(fileInputStream);

        String browser_properties = properties.getProperty("browser");
        String browser_maven = System.getProperty("browser");
        String browser = browser_maven != null ? browser_maven : browser_properties;

        if (driver == null) {
            if (browser.equals("Chrome")) {
                System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
                driver = new ChromeDriver();
            } else {
                throw new Exception("Please implement support for this browser");
            }
            driver.get(properties.getProperty("QAUrl"));
        }

        return driver;
    }
}