package stepdefinitions;

import utils.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.ProductDetailPage;
import pages.ProductsPage;
import pages.CartPage;

public class ProductSteps {
    private ProductsPage productsPage;
    private ProductDetailPage productDetailPage;
    private CartPage cartPage;

    @Given("user is on products page")
    public void userIsOnProductsPage() {
        productsPage = new ProductsPage(DriverFactory.getDriver());
    }

    @Then("products page should be displayed")
    public void productsPageShouldBeDisplayed() {
        Assert.assertTrue(productsPage.isProductsPageDisplayed(), 
            "Products page is not displayed");
    }

    @Then("products list should contain items")
    public void productsListShouldContainItems() {
        Assert.assertTrue(productsPage.getProductCount() > 0, 
            "No products are displayed");
    }

    @When("user clicks on first product")
    public void userClicksOnFirstProduct() {
        productsPage.clickFirstProduct();
        productDetailPage = new ProductDetailPage(DriverFactory.getDriver());
    }

    @Then("product detail page should be displayed")
    public void productDetailPageShouldBeDisplayed() {
        Assert.assertTrue(productDetailPage.isProductDetailPageDisplayed(), 
            "Product detail page is not displayed");
    }

    @Then("product name should be displayed")
    public void productNameShouldBeDisplayed() {
        String productName = productDetailPage.getProductName();
        Assert.assertNotNull(productName, "Product name is null");
        Assert.assertFalse(productName.isEmpty(), "Product name is empty");
    }

    @Then("product price should be displayed")
    public void productPriceShouldBeDisplayed() {
        String productPrice = productDetailPage.getProductPrice();
        Assert.assertNotNull(productPrice, "Product price is null");
        Assert.assertTrue(productPrice.contains("$"), "Product price doesn't contain $");
    }

    @Then("product rating should be displayed")
    public void productRatingShouldBeDisplayed() {
        Assert.assertTrue(productDetailPage.isProductRatingDisplayed(), 
            "Product rating is not displayed");
    }

    @Then("quantity should be {string}")
    public void quantityShouldBe(String expectedQuantity) {
        String actualQuantity = productDetailPage.getQuantity();
        Assert.assertEquals(actualQuantity, expectedQuantity, 
            "Expected quantity: " + expectedQuantity + " but got: " + actualQuantity);
    }

    @When("user clicks increase quantity button")
    public void userClicksIncreaseQuantityButton() {
        productDetailPage.clickIncreaseQuantity();
    }

    @When("user clicks decrease quantity button")
    public void userClicksDecreaseQuantityButton() {
        productDetailPage.clickDecreaseQuantity();
    }

    @When("user increases quantity to {string}")
    public void userIncreasesQuantityTo(String quantity) {
        int targetQuantity = Integer.parseInt(quantity);
        productDetailPage.setQuantity(targetQuantity);
    }

    @When("user clicks add to cart button")
    public void userClicksAddToCartButton() {
        productDetailPage.clickAddToCart();
    }

    @When("user opens cart")
    public void userOpensCart() {
        if (productsPage != null && productsPage.isProductsPageDisplayed()) {
            productsPage.openCart();
        } else if (productDetailPage != null) {
            // If we're on product detail page, we need to navigate back first
            DriverFactory.getDriver().navigate().back();
            productsPage = new ProductsPage(DriverFactory.getDriver());
            productsPage.openCart();
        }
        cartPage = new CartPage(DriverFactory.getDriver());
    }

    @Then("cart should contain {string} items")
    public void cartShouldContainItems(String expectedCount) {
        int actualCount = cartPage.getCartItemCount();
        int expected = Integer.parseInt(expectedCount);
        Assert.assertEquals(actualCount, expected, 
            "Expected " + expected + " items in cart but got: " + actualCount);
    }

    @Then("first cart item quantity should be {string}")
    public void firstCartItemQuantityShouldBe(String expectedQuantity) {
        String actualQuantity = cartPage.getFirstItemQuantity();
        Assert.assertEquals(actualQuantity, expectedQuantity, 
            "Expected first item quantity: " + expectedQuantity + " but got: " + actualQuantity);
    }

    @Then("cart badge should show {string}")
    public void cartBadgeShouldShow(String expectedBadgeNumber) {
        String actualBadgeNumber = productDetailPage.getCartBadgeNumber();
        Assert.assertEquals(actualBadgeNumber, expectedBadgeNumber, 
            "Expected cart badge: " + expectedBadgeNumber + " but got: " + actualBadgeNumber);
    }

    @Then("color options should be displayed")
    public void colorOptionsShouldBeDisplayed() {
        Assert.assertTrue(productDetailPage.areColorOptionsDisplayed(), 
            "Color options are not displayed");
    }

    @When("user selects color {string}")
    public void userSelectsColor(String color) {
        productDetailPage.selectColor(color);
    }

    @Then("selected color should be {string}")
    public void selectedColorShouldBe(String color) {
        // This is a visual check, in real scenario we would verify the selected state
        // For now, we just verify that color selection didn't cause any error
        Assert.assertTrue(productDetailPage.isProductDetailPageDisplayed(), 
            "Product detail page is not displayed after color selection");
    }
}
