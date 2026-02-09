package pages;

import java.time.Duration;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.ElementHelper;

public class BasePage {
    protected AppiumDriver driver;
    protected ElementHelper elementHelper;

    public BasePage(AppiumDriver driver) {
        this.driver = driver;
        this.elementHelper = new ElementHelper(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }
}
