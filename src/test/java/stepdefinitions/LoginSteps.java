package stepdefinitions;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import utils.ConfigReader;
import utils.DriverFactory;

public class LoginSteps {
    private LoginPage loginPage;

    @Given("user is on products page")
    public void userIsOnProductsPage() {
        loginPage = new LoginPage(DriverFactory.getDriver());
        System.out.println("Application launched - User is on products page");
    }

    @Given("user launches the application")
    public void userLaunchesTheApplication() {
        loginPage = new LoginPage(DriverFactory.getDriver());
        System.out.println("Application launched successfully");
    }

    @When("user logs in with valid credentials")
    public void userLogsInWithValidCredentials() {
        String username = ConfigReader.getValidUsername();
        String password = ConfigReader.getValidPassword();
        loginPage.login(username, password);
    }

    @When("user logs in with username {string} and password {string}")
    public void userLogsInWithUsernameAndPassword(String username, String password) {
        loginPage.login(username, password);
    }

    @When("user opens menu")
    public void userOpensMenu() {
        loginPage.openMenu();
    }

    @When("user clicks on login menu item")
    public void userClicksOnLoginMenuItem() {
        loginPage.clickLoginMenuItem();
    }

    @When("user enters username {string}")
    public void userEntersUsername(String username) {
        loginPage.enterUsername(username);
    }

    @When("user enters password {string}")
    public void userEntersPassword(String password) {
        loginPage.enterPassword(password);
    }

    @When("user clicks login button")
    public void userClicksLoginButton() {
        loginPage.clickLoginButton();
    }

    @Then("user should be redirected to the products page")
    public void userShouldBeRedirectedToTheProductsPage() {
        Assert.assertTrue(loginPage.isProductPageDisplayed(), 
            "User is not redirected to products page");
    }

    @Then("user should see an error message")
    public void userShouldSeeAnErrorMessage() {
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
            "Error message is not displayed");
    }

    @Then("error message should contain {string}")
    public void errorMessageShouldContain(String expectedText) {
        String actualMessage = loginPage.getErrorMessage();
        Assert.assertTrue(actualMessage.contains(expectedText), 
            "Expected error message to contain: " + expectedText + " but got: " + actualMessage);
    }
}
