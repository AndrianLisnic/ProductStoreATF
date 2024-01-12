package stepDefinitions;

import context.TestContextSetup;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pageObjects.ProductPage;
import utils.GenericUtils;

public class ProductPageStepDefinition {
    TestContextSetup testContextSetup;
    ProductPage productPage;
    Logger logger;

    public ProductPageStepDefinition(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
        this.productPage = testContextSetup.pageObjectManager.getProductPage();
        logger = LogManager.getLogger(GenericUtils.class);
    }

    @And("user adds the selected product to the cart")
    public void userAddsTheSelectedProductToTheCart() {
        productPage.clickOnAddToCardButton(testContextSetup);
        productPage.saveProductPriceInContext(testContextSetup);
    }

    @And("user closes the confirmation pop-up message")
    public void userClosesTheConfirmationPopUpMessage() {
        productPage.closePopUpConfirmationMessage(testContextSetup);
    }
}
