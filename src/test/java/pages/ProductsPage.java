package pages;

import java.util.List;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ProductsPage extends BasePage {

    @AndroidFindBy(id = "/com.saucelabs.mydemoapp.android:id/productTV")
    @iOSXCUITFindBy(id = "/com.saucelabs.mydemoapp.android:id/productTV")
    private WebElement pageTitle;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@content-desc, 'store item')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[contains(@name, 'store item')]")
    private List<WebElement> productItems;

    @AndroidFindBy(xpath = "(//android.widget.ImageView[@content-desc=\"Product Image\"])[1]")
    @iOSXCUITFindBy(xpath = "(//android.widget.ImageView[@content-desc=\"Product Image\"])[1]")
    private WebElement firstProduct;

    @AndroidFindBy(accessibility = "View menu")
    @iOSXCUITFindBy(accessibility = "View menu")
    private WebElement menuButton;

    @AndroidFindBy(accessibility = "Logout Menu Item")
    @iOSXCUITFindBy(accessibility = "Logout Menu Item")
    private WebElement logoutMenuItem;

    @AndroidFindBy(accessibility = "Login Menu Item")
    @iOSXCUITFindBy(accessibility = "Login Menu Item")
    private WebElement logInButton;

    @AndroidFindBy(accessibility = "Displays number of items in your cart")
    @iOSXCUITFindBy(accessibility = "Displays number of items in your cart")
    private WebElement cartBadge;

    public ProductsPage(AppiumDriver driver) {
        super(driver);
    }

    public boolean isProductsPageDisplayed() {
        return elementHelper.isDisplayed(pageTitle);
    }

    public String getPageTitle() {
        return elementHelper.getText(pageTitle);
    }

    public int getProductCount() {
        return productItems.size();
    }

    public void clickFirstProduct() {
        elementHelper.click(firstProduct);
        elementHelper.waitForSeconds(1);
    }

    public void openMenu() {
        elementHelper.click(menuButton);
        elementHelper.waitForSeconds(1);
    }

    public void logout() {
        openMenu();
        elementHelper.click(logoutMenuItem);
        elementHelper.waitForSeconds(1);
    }

    public boolean isLogInButtonDisplayed() {
        return elementHelper.isDisplayed(logInButton);
    }

    public void openCart() {
        elementHelper.click(cartBadge);
        elementHelper.waitForSeconds(1);
    }

    public String getCartBadgeNumber() {
        if (elementHelper.isDisplayed(cartBadge)) {
            return elementHelper.getText(cartBadge);
        }
        return "0";
    }

    public boolean isCartBadgeDisplayed() {
        return elementHelper.isDisplayed(cartBadge);
    }
}
