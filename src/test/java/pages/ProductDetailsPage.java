package pages;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ProductDetailsPage extends BasePage {

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='container header']/android.widget.TextView")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='container header']/XCUIElementTypeStaticText")
    private WebElement productName;

    @AndroidFindBy(accessibility = "product price")
    @iOSXCUITFindBy(accessibility = "product price")
    private WebElement productPrice;

    @AndroidFindBy(accessibility = "Add To Cart button")
    @iOSXCUITFindBy(accessibility = "Add To Cart button")
    private WebElement addToCartButton;

    @AndroidFindBy(accessibility = "counter amount")
    @iOSXCUITFindBy(accessibility = "counter amount")
    private WebElement cartCounter;

    @AndroidFindBy(accessibility = "counter plus button")
    @iOSXCUITFindBy(accessibility = "counter plus button")
    private WebElement increaseQuantityButton;

    @AndroidFindBy(accessibility = "counter minus button")
    @iOSXCUITFindBy(accessibility = "counter minus button")
    private WebElement decreaseQuantityButton;

    public ProductDetailsPage(AppiumDriver driver) {
        super(driver);
    }

    public String getProductName() {
        return elementHelper.getText(productName);
    }

    public String getProductPrice() {
        return elementHelper.getText(productPrice);
    }

    public void clickAddToCart() {
        elementHelper.click(addToCartButton);
        elementHelper.waitForSeconds(1);
    }

    public void increaseQuantity(int times) {
        for (int i = 0; i < times; i++) {
            elementHelper.click(increaseQuantityButton);
            elementHelper.waitForSeconds(1);
        }
    }

    public void decreaseQuantity(int times) {
        for (int i = 0; i < times; i++) {
            elementHelper.click(decreaseQuantityButton);
            elementHelper.waitForSeconds(1);
        }
    }

    public String getQuantity() {
        return elementHelper.getText(cartCounter);
    }
}
