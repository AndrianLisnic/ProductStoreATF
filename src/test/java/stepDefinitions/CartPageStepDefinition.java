package stepDefinitions;

import context.TestContextSetup;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import dataTable.OrderDetails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pageObjects.CartPage;
import utils.GenericUtils;

import java.util.List;
import java.util.Map;

public class CartPageStepDefinition {
    TestContextSetup testContextSetup;
    CartPage cartPage;
    Logger logger;

    public CartPageStepDefinition(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
        this.cartPage = testContextSetup.pageObjectManager.getCartPage();
        logger = LogManager.getLogger(GenericUtils.class);
    }

    @Then("validate the product name and price are correctly displayed")
    public void validateTheProductNameAndPriceAreCorrectlyDisplayed() {
        cartPage.validateProductNameAndPriceInCart(testContextSetup);
    }

    @And("user clicks on Place Order button from the Place Order modal")
    public void userClicksOnPlaceOrderButton() {
        cartPage.clickOnPlaceOrderButton();
        testContextSetup.genericUtils.switchToActiveElement();
    }

    @And("^user fills in the order details in the modal window with following details$")
    public void userFillsInTheOrderDetailsInTheModalWindow(List<OrderDetails> orderDetailsList) {
        cartPage.fillInTheOrderDetails(orderDetailsList, testContextSetup);
    }

    @And("user clicks on Purchase button")
    public void userClicksOnPurchaseButton() {
        cartPage.clickOnPurchaseButton();
    }

    @Then("validate the purchase details are correctly displayed")
    public void validateThePurchaseDetailsAreCorrectlyDisplayed() {
        cartPage.validatePurchaseDetailsInConformationMessage(testContextSetup);
    }

    @Then("user clicks the OK button from the confirmation pop-up")
    public void userClicksTheOKButtonFromTheConfirmationPopUp() {
        cartPage.clickOKButtonFromTheConfirmationPopUp();
    }

    @DataTableType
    public OrderDetails orderDetailsEntry(Map<String, String> entry) {
        return new OrderDetails(
                entry.get("name"),
                entry.get("country"),
                entry.get("city"),
                entry.get("creditCard"),
                entry.get("month"),
                entry.get("year"));
    }
}
