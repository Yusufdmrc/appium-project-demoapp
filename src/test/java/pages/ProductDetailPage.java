package pages;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ProductDetailPage extends BasePage {

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/productTV")
    @iOSXCUITFindBy(id = "com.saucelabs.mydemoapp.android:id/productTV")
    private WebElement productName;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/priceTV")
    @iOSXCUITFindBy(id = "com.saucelabs.mydemoapp.android:id/priceTV")
    private WebElement productPrice;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/ratingBar")
    @iOSXCUITFindBy(id = "com.saucelabs.mydemoapp.android:id/ratingBar")
    private WebElement productRating;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/productIV")
    @iOSXCUITFindBy(id = "com.saucelabs.mydemoapp.android:id/productIV")
    private WebElement productImage;

    @AndroidFindBy(accessibility = "counter plus button")
    @iOSXCUITFindBy(accessibility = "counter plus button")
    private WebElement increaseQuantityButton;

    @AndroidFindBy(accessibility = "counter minus button")
    @iOSXCUITFindBy(accessibility = "counter minus button")
    private WebElement decreaseQuantityButton;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/counterTV")
    @iOSXCUITFindBy(id = "com.saucelabs.mydemoapp.android:id/counterTV")
    private WebElement quantityText;

    @AndroidFindBy(accessibility = "Add To Cart button")
    @iOSXCUITFindBy(accessibility = "Add To Cart button")
    private WebElement addToCartButton;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='black circle']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='black circle']")
    private WebElement blackColorOption;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='blue circle']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='blue circle']")
    private WebElement blueColorOption;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='gray circle']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='gray circle']")
    private WebElement grayColorOption;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='green circle']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='green circle']")
    private WebElement greenColorOption;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/productDescriptionTV")
    @iOSXCUITFindBy(id = "com.saucelabs.mydemoapp.android:id/productDescriptionTV")
    private WebElement productDescription;

    @AndroidFindBy(accessibility = "cart badge")
    @iOSXCUITFindBy(accessibility = "cart badge")
    private WebElement cartBadge;

    public ProductDetailPage(AppiumDriver driver) {
        super(driver);
    }

    public boolean isProductDetailPageDisplayed() {
        return elementHelper.isDisplayed(productName) && 
               elementHelper.isDisplayed(productPrice);
    }

    public String getProductName() {
        return elementHelper.getText(productName);
    }

    public String getProductPrice() {
        return elementHelper.getText(productPrice);
    }

    public boolean isProductRatingDisplayed() {
        return elementHelper.isDisplayed(productRating);
    }

    public boolean isProductImageDisplayed() {
        return elementHelper.isDisplayed(productImage);
    }

    public String getQuantity() {
        return elementHelper.getText(quantityText);
    }

    public void clickIncreaseQuantity() {
        elementHelper.click(increaseQuantityButton);
        elementHelper.waitForSeconds(1);
    }

    public void clickDecreaseQuantity() {
        elementHelper.click(decreaseQuantityButton);
        elementHelper.waitForSeconds(1);
    }

    public void setQuantity(int quantity) {
        int currentQuantity = Integer.parseInt(getQuantity());
        int difference = quantity - currentQuantity;
        
        if (difference > 0) {
            for (int i = 0; i < difference; i++) {
                clickIncreaseQuantity();
            }
        } else if (difference < 0) {
            for (int i = 0; i < Math.abs(difference); i++) {
                clickDecreaseQuantity();
            }
        }
    }

    public void clickAddToCart() {
        elementHelper.click(addToCartButton);
        elementHelper.waitForSeconds(2);
    }

    public boolean areColorOptionsDisplayed() {
        return elementHelper.isDisplayed(blackColorOption) || 
               elementHelper.isDisplayed(blueColorOption);
    }

    public void selectColor(String color) {
        switch (color.toLowerCase()) {
            case "black":
                elementHelper.click(blackColorOption);
                break;
            case "blue":
                elementHelper.click(blueColorOption);
                break;
            case "gray":
            case "grey":
                elementHelper.click(grayColorOption);
                break;
            case "green":
                elementHelper.click(greenColorOption);
                break;
            default:
                throw new IllegalArgumentException("Invalid color: " + color);
        }
        elementHelper.waitForSeconds(1);
    }

    public String getCartBadgeNumber() {
        if (elementHelper.isDisplayed(cartBadge)) {
            return elementHelper.getText(cartBadge);
        }
        return "0";
    }

    public boolean isProductDescriptionDisplayed() {
        return elementHelper.isDisplayed(productDescription);
    }

    public String getProductDescription() {
        return elementHelper.getText(productDescription);
    }
}
