package utils;

import java.time.Duration;
import java.util.Collections;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;

public class ElementHelper {
    private AppiumDriver driver;
    private WebDriverWait wait;

    public ElementHelper(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()));
    }

    // Wait Methods
    public WebElement waitForElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public boolean waitForElementToBeInvisible(WebElement element) {
        return wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitForSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Wait interrupted: " + e.getMessage());
        }
    }

    // Click Methods
    public void click(WebElement element) {
        waitForElementToBeClickable(element).click();
    }

    public void clickIfDisplayed(WebElement element) {
        if (isDisplayed(element)) {
            click(element);
        }
    }

    // SendKeys Methods
    public void sendKeys(WebElement element, String text) {
        waitForElementToBeVisible(element);
        element.clear();
        element.sendKeys(text);
    }

    public void sendKeysWithoutClear(WebElement element, String text) {
        waitForElementToBeVisible(element).sendKeys(text);
    }

    // Get Methods
    public String getText(WebElement element) {
        return waitForElementToBeVisible(element).getText();
    }

    public String getAttribute(WebElement element, String attributeName) {
        return waitForElementToBeVisible(element).getAttribute(attributeName);
    }

    // Element State Methods
    public boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isEnabled(WebElement element) {
        try {
            return waitForElementToBeVisible(element).isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSelected(WebElement element) {
        try {
            return waitForElementToBeVisible(element).isSelected();
        } catch (Exception e) {
            return false;
        }
    }

    // Clear Method
    public void clear(WebElement element) {
        waitForElementToBeVisible(element).clear();
    }

    // Scroll Methods
    public void scrollToElement(WebElement element) {
        Point location = element.getLocation();
        Dimension size = driver.manage().window().getSize();
        
        int startX = size.width / 2;
        int startY = size.height * 3 / 4;
        int endX = startX;
        int endY = location.getY();
        
        scroll(startX, startY, endX, endY);
    }

    public void scrollDown() {
        Dimension size = driver.manage().window().getSize();
        int startX = size.width / 2;
        int startY = size.height * 3 / 4;
        int endY = size.height / 4;
        
        scroll(startX, startY, startX, endY);
    }

    public void scrollUp() {
        Dimension size = driver.manage().window().getSize();
        int startX = size.width / 2;
        int startY = size.height / 4;
        int endY = size.height * 3 / 4;
        
        scroll(startX, startY, startX, endY);
    }

    // Swipe Methods
    public void swipeLeft() {
        Dimension size = driver.manage().window().getSize();
        int startX = size.width * 3 / 4;
        int endX = size.width / 4;
        int y = size.height / 2;
        
        scroll(startX, y, endX, y);
    }

    public void swipeRight() {
        Dimension size = driver.manage().window().getSize();
        int startX = size.width / 4;
        int endX = size.width * 3 / 4;
        int y = size.height / 2;
        
        scroll(startX, y, endX, y);
    }

    public void swipeOnElement(WebElement element, String direction) {
        Point location = element.getLocation();
        Dimension size = element.getSize();
        
        int centerX = location.getX() + (size.width / 2);
        int centerY = location.getY() + (size.height / 2);
        
        int startX, startY, endX, endY;
        
        switch (direction.toLowerCase()) {
            case "left":
                startX = centerX + (size.width / 4);
                endX = centerX - (size.width / 4);
                startY = endY = centerY;
                break;
            case "right":
                startX = centerX - (size.width / 4);
                endX = centerX + (size.width / 4);
                startY = endY = centerY;
                break;
            case "up":
                startY = centerY + (size.height / 4);
                endY = centerY - (size.height / 4);
                startX = endX = centerX;
                break;
            case "down":
                startY = centerY - (size.height / 4);
                endY = centerY + (size.height / 4);
                startX = endX = centerX;
                break;
            default:
                throw new IllegalArgumentException("Invalid swipe direction: " + direction);
        }
        
        scroll(startX, startY, endX, endY);
    }

    // Core Scroll Implementation
    private void scroll(int startX, int startY, int endX, int endY) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence scroll = new Sequence(finger, 1);
        
        scroll.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        scroll.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), endX, endY));
        scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        
        driver.perform(Collections.singletonList(scroll));
    }

    // Long Press
    public void longPress(WebElement element, int durationInSeconds) {
        Point location = element.getLocation();
        Dimension size = element.getSize();
        
        int centerX = location.getX() + (size.width / 2);
        int centerY = location.getY() + (size.height / 2);
        
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence longPress = new Sequence(finger, 1);
        
        longPress.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, centerY));
        longPress.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        longPress.addAction(finger.createPointerMove(Duration.ofSeconds(durationInSeconds), PointerInput.Origin.viewport(), centerX, centerY));
        longPress.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        
        driver.perform(Collections.singletonList(longPress));
    }

    // Tap at specific coordinates
    public void tapAtCoordinates(int x, int y) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1);
        
        tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        
        driver.perform(Collections.singletonList(tap));
    }
}
