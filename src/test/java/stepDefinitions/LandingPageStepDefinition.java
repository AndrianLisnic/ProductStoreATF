package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import pageObjects.LandingPage;
import utils.GenericUtils;
import context.TestContextSetup;

import static enums.DataKeys.PASSWORD;
import static enums.DataKeys.USERNAME;

public class LandingPageStepDefinition {
    TestContextSetup testContextSetup;
    LandingPage landingPage;
    Logger logger;

    public LandingPageStepDefinition(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
        this.landingPage = testContextSetup.pageObjectManager.getLandingPage();
        logger = LogManager.getLogger(GenericUtils.class);
    }

    @Given("user is on Product Store Landing page")
    public void userIsOnProductStoreLandingPage() {
        Assert.assertTrue(landingPage.getLandingPageTitle().contains("STORE"), "The user is on Product Store landing page");
        logger.info("The user is on Product Store landing page");
    }

    @When("^user clicks on (LogIn|LogOut) link$")
    public void userClicksOnLogInOrLogOutLink(String linkName) {
        switch (linkName) {
            case "LogIn":
                landingPage.clickOnLogInLink();
                break;
            case "LogOut":
                landingPage.clickOnLogOutLink();
                break;
            default:
                throw new RuntimeException("Unknown link name");
        }
    }

    @Then("^(.*) modal window is displayed$")
    public void signInModalWindowIsDisplayed(String modalName) {
        landingPage.validateModalWindowIsDisplayed(modalName, testContextSetup);
    }

    @And("^user enters (.*) as username and (.*) as password$")
    public void userEntersAsUsernameAndAsPassword(String username, String password) {
        landingPage.insertUsernameAndPassword(username, password);
        testContextSetup.save(USERNAME, username);
        testContextSetup.save(PASSWORD, password);
    }

    @And("user clicks on Sign Up button")
    public void userClicksOnSignUpButton() {
        landingPage.clickOnLogInButton();
    }

    @Then("user is successfully logged in")
    public void userIsSuccessfullyLoggedIn() {
        String profileButtonName = landingPage.getProfileButtonName(testContextSetup);
        Assert.assertEquals(profileButtonName.substring(profileButtonName.lastIndexOf(" ") + 1), testContextSetup.getData(USERNAME));
        logger.info(String.format("The user %s is successfully logged in.", testContextSetup.getData(USERNAME)));
    }

    @And("user selects a random product from the Landing page")
    public void userSelectsARandomProductFromTheHomePage() {
        landingPage.selectARandomProductFromLandingPage(testContextSetup);
    }

    @When("user opens the shopping cart")
    public void userOpensTheShoppingCart() {
        landingPage.openShoppingCart();
    }
}
