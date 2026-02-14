package pages;

import java.util.List;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class CartPage extends BasePage {

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='container header']/android.widget.TextView")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='container header']/XCUIElementTypeStaticText")
    private WebElement pageTitle;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@content-desc, 'product row')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[contains(@name, 'product row')]")
    private List<WebElement> cartItems;

    @AndroidFindBy(accessibility = "total price")
    @iOSXCUITFindBy(accessibility = "total price")
    private WebElement totalPrice;

    @AndroidFindBy(accessibility = "Proceed To Checkout button")
    @iOSXCUITFindBy(accessibility = "Proceed To Checkout button")
    private WebElement checkoutButton;

    @AndroidFindBy(accessibility = "remove item")
    @iOSXCUITFindBy(accessibility = "remove item")
    private WebElement removeItemButton;

    @AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc='counter'])[1]")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='counter'])[1]")
    private WebElement firstItemQuantity;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='No Items']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='No Items']")
    private WebElement noItemsMessage;

    public CartPage(AppiumDriver driver) {
        super(driver);
    }

    public boolean isCartPageDisplayed() {
        return elementHelper.isDisplayed(pageTitle);
    }

    public String getPageTitle() {
        return elementHelper.getText(pageTitle);
    }

    public int getCartItemCount() {
        return cartItems.size();
    }

    public String getTotalPrice() {
        return elementHelper.getText(totalPrice);
    }

    public void proceedToCheckout() {
        elementHelper.click(checkoutButton);
        elementHelper.waitForSeconds(1);
    }

    public void removeFirstItem() {
        elementHelper.click(removeItemButton);
        elementHelper.waitForSeconds(1);
    }

    public boolean isCartEmpty() {
        return elementHelper.isDisplayed(noItemsMessage);
    }

    public String getFirstItemQuantity() {
        if (elementHelper.isDisplayed(firstItemQuantity)) {
            return elementHelper.getText(firstItemQuantity);
        }
        return "0";
    }

    public boolean hasItems() {
        return !cartItems.isEmpty();
    }
}
