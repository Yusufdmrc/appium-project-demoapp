package stepdefinitions;

import utils.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.ProductDetailsPage;
import pages.ProductsPage;

public class ProductSteps {
    private ProductsPage productsPage;
    private ProductDetailsPage productDetailsPage;

    @Given("user is on the products page")
    public void userIsOnTheProductsPage() {
        productsPage = new ProductsPage(DriverFactory.getDriver());
        Assert.assertTrue(productsPage.isProductsPageDisplayed(), 
            "Products page is not displayed");
    }

    @Then("user should see the products page")
    public void userShouldSeeTheProductsPage() {
        productsPage = new ProductsPage(DriverFactory.getDriver());
        Assert.assertTrue(productsPage.isProductsPageDisplayed(), 
            "Products page is not displayed");
    }

    @Then("products should be displayed")
    public void productsShouldBeDisplayed() {
        Assert.assertTrue(productsPage.getProductCount() > 0, 
            "No products are displayed");
    }

    @When("user clicks on the first product")
    public void userClicksOnTheFirstProduct() {
        productsPage.clickFirstProduct();
        productDetailsPage = new ProductDetailsPage(DriverFactory.getDriver());
    }

    @Then("product details should be displayed")
    public void productDetailsShouldBeDisplayed() {
        Assert.assertNotNull(productDetailsPage.getProductName(), 
            "Product name is not displayed");
        Assert.assertNotNull(productDetailsPage.getProductPrice(), 
            "Product price is not displayed");
    }

    @When("user adds the product to cart")
    public void userAddsTheProductToCart() {
        productDetailsPage.clickAddToCart();
    }

    @When("user increases quantity by {int}")
    public void userIncreasesQuantityBy(int times) {
        productDetailsPage.increaseQuantity(times);
    }

    @When("user logs out")
    public void userLogsOut() {
        productsPage.logout();
    }

    @Then("user should see login button")
    public void userShouldSeeLoginButton() {
        Assert.assertTrue(productsPage.isLogInButtonDisplayed(), 
            "Login button is not displayed after logout");
    }
}
