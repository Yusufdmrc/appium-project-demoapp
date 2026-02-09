package pages;

import java.util.List;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ProductsPage extends BasePage {

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='container header']/android.widget.TextView")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='container header']/XCUIElementTypeStaticText")
    private WebElement pageTitle;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@content-desc, 'store item')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[contains(@name, 'store item')]")
    private List<WebElement> productItems;

    @AndroidFindBy(xpath = "(//android.view.ViewGroup[contains(@content-desc, 'store item')])[1]")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[contains(@name, 'store item')])[1]")
    private WebElement firstProduct;

    @AndroidFindBy(accessibility = "open menu")
    @iOSXCUITFindBy(accessibility = "open menu")
    private WebElement menuButton;

    @AndroidFindBy(accessibility = "menu item log out")
    @iOSXCUITFindBy(accessibility = "menu item log out")
    private WebElement logoutMenuItem;

    @AndroidFindBy(accessibility = "Log In")
    @iOSXCUITFindBy(accessibility = "Log In")
    private WebElement logInButton;

    @AndroidFindBy(accessibility = "cart badge")
    @iOSXCUITFindBy(accessibility = "cart badge")
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
}
