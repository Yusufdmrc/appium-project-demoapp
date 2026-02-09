package utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;

public class Hooks {

    @Before
    public void setUp(Scenario scenario) {
        System.out.println("========================================");
        System.out.println("Starting Scenario: " + scenario.getName());
        System.out.println("========================================");
        DriverFactory.initializeDriver();
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        if (scenario.isFailed()) {
            System.out.println("Step Failed in Scenario: " + scenario.getName());
            
            // Capture screenshot for failed steps
            byte[] screenshot = ScreenshotUtils.captureScreenshotAsBytes(DriverFactory.getDriver());
            
            // Attach to Cucumber report
            String stepName = "Failed_Step_" + System.currentTimeMillis();
            scenario.attach(screenshot, "image/png", stepName);
            
            // Attach to Allure report
            Allure.addAttachment(stepName, new ByteArrayInputStream(screenshot));
            
            // Save to file system
            ScreenshotUtils.captureScreenshot(DriverFactory.getDriver(), stepName);
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        System.out.println("========================================");
        System.out.println("Scenario Status: " + scenario.getStatus());
        System.out.println("========================================");
        
        DriverFactory.quitDriver();
    }

    public static class ScreenshotUtils {

        public static String captureScreenshot(AppiumDriver driver, String screenshotName) {
            if (!ConfigReader.isScreenshotEnabled()) {
                return null;
            }

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = screenshotName + "_" + timestamp + ".png";
            String screenshotPath = ConfigReader.getScreenshotPath() + "/" + fileName;

            try {
                File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                File destinationFile = new File(screenshotPath);
                FileUtils.copyFile(screenshotFile, destinationFile);
                System.out.println("Screenshot saved: " + screenshotPath);
                return screenshotPath;
            } catch (IOException e) {
                System.err.println("Failed to capture screenshot: " + e.getMessage());
                return null;
            }
        }

        public static byte[] captureScreenshotAsBytes(AppiumDriver driver) {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        }
    }
}
