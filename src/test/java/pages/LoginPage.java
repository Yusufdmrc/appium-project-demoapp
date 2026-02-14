package pages;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class LoginPage extends BasePage {

    @AndroidFindBy(accessibility = "View menu")
    @iOSXCUITFindBy(accessibility = "View menu")
    private WebElement menuButton;

    @AndroidFindBy(accessibility = "Login Menu Item")
    @iOSXCUITFindBy(accessibility = "Login Menu Item")
    private WebElement loginMenuItem;

    @AndroidFindBy(id= "com.saucelabs.mydemoapp.android:id/nameET")
    @iOSXCUITFindBy(id = "com.saucelabs.mydemoapp.android:id/nameET")
    private WebElement usernameField;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/passwordET")
    @iOSXCUITFindBy(id = "com.saucelabs.mydemoapp.android:id/passwordET")
    private WebElement passwordField;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/loginBtn")
    @iOSXCUITFindBy(id = "com.saucelabs.mydemoapp.android:id/loginBtn")
    private WebElement loginButton;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/productTV")
    @iOSXCUITFindBy(id = "com.saucelabs.mydemoapp.android:id/productTV")
    private WebElement productHeaderTitle;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/nameErrorTV")
    @iOSXCUITFindBy(accessibility = "com.saucelabs.mydemoapp.android:id/nameErrorTV")
    private WebElement usernameErrorMessage;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/passwordErrorTV")
    @iOSXCUITFindBy(accessibility = "com.saucelabs.mydemoapp.android:id/passwordErrorTV")
    private WebElement passwordErrorMessage;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/passwordErrorTV")
    @iOSXCUITFindBy(accessibility = "com.saucelabs.mydemoapp.android:id/passwordErrorTV")
    private WebElement genericErrorMessage;

    public LoginPage(AppiumDriver driver) {
        super(driver);
    }

    public void openMenu() {
        elementHelper.click(menuButton);
        elementHelper.waitForSeconds(1);
    }

    public void clickLoginMenuItem() {
        elementHelper.click(loginMenuItem);
        elementHelper.waitForSeconds(1);
    }

    public void enterUsername(String username) {
        elementHelper.sendKeys(usernameField, username);
    }

    public void enterPassword(String password) {
        elementHelper.sendKeys(passwordField, password);
    }

    public void clickLoginButton() {
        elementHelper.click(loginButton);
        elementHelper.waitForSeconds(2);
    }

    public void login(String username, String password) {
        openMenu();
        clickLoginMenuItem();
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public boolean isProductPageDisplayed() {
        return elementHelper.isDisplayed(productHeaderTitle);
    }

   public String getErrorMessage() {
    if (elementHelper.isDisplayed(usernameErrorMessage)) {
        return elementHelper.getText(usernameErrorMessage);
    } else if (elementHelper.isDisplayed(passwordErrorMessage)) {
        return elementHelper.getText(passwordErrorMessage);
    } else if (elementHelper.isDisplayed(genericErrorMessage)) {
        return elementHelper.getText(genericErrorMessage);
    }
    return "";
}

  public boolean isErrorMessageDisplayed() {
    return elementHelper.isDisplayed(usernameErrorMessage) || 
           elementHelper.isDisplayed(passwordErrorMessage) || 
           elementHelper.isDisplayed(genericErrorMessage);
}
}
