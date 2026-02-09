package stepdefinitions;

import utils.DriverFactory;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.CartPage;
import pages.ProductsPage;

public class CartSteps {
    private CartPage cartPage;
    private ProductsPage productsPage;

    @When("user opens the cart")
    public void userOpensTheCart() {
        productsPage = new ProductsPage(DriverFactory.getDriver());
        productsPage.openCart();
        cartPage = new CartPage(DriverFactory.getDriver());
    }

    @Then("cart should display the added item")
    public void cartShouldDisplayTheAddedItem() {
        Assert.assertTrue(cartPage.getCartItemCount() > 0, 
            "Cart is empty");
    }

    @Then("cart should contain {int} item(s)")
    public void cartShouldContainItems(int expectedCount) {
        Assert.assertEquals(cartPage.getCartItemCount(), expectedCount, 
            "Cart item count mismatch");
    }

    @When("user removes the first item from cart")
    public void userRemovesTheFirstItemFromCart() {
        cartPage.removeFirstItem();
    }

    @Then("cart should be empty")
    public void cartShouldBeEmpty() {
        Assert.assertTrue(cartPage.isCartEmpty(), 
            "Cart is not empty");
    }

    @When("user proceeds to checkout")
    public void userProceedsToCheckout() {
        cartPage.proceedToCheckout();
    }

    @Then("user should see cart page")
    public void userShouldSeeCartPage() {
        Assert.assertTrue(cartPage.isCartPageDisplayed(), 
            "Cart page is not displayed");
    }

    @Then("total price should be displayed")
    public void totalPriceShouldBeDisplayed() {
        String totalPrice = cartPage.getTotalPrice();
        Assert.assertNotNull(totalPrice, "Total price is not displayed");
        Assert.assertTrue(totalPrice.contains("$"), 
            "Total price format is incorrect");
    }
}
