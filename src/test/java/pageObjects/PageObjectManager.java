package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {
    public WebDriver driver;
    public LandingPage landingPage;
    public ProductPage productPage;
    public CartPage cartPage;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public LandingPage getLandingPage() {
        landingPage = new LandingPage(driver);
        return landingPage;
    }

    public ProductPage getProductPage() {
        productPage = new ProductPage(driver);
        return productPage;
    }
    public CartPage getCartPage() {
        cartPage = new CartPage(driver);
        return cartPage;
    }
}
