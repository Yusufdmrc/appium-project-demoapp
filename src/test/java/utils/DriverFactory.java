package utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

public class DriverFactory {
    private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    public static AppiumDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(AppiumDriver appiumDriver) {
        driver.set(appiumDriver);
    }

    public static AppiumDriver initializeDriver() {
        String platform = ConfigReader.getPlatform().toLowerCase();
        AppiumDriver appiumDriver;

        try {
            URL serverUrl = new URL(ConfigReader.getAppiumServerUrl());

            if (platform.equals("android")) {
                appiumDriver = createAndroidDriver(serverUrl);
            } else if (platform.equals("ios")) {
                appiumDriver = createIOSDriver(serverUrl);
            } else {
                throw new IllegalArgumentException("Invalid platform: " + platform);
            }

            appiumDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getImplicitWait()));
            setDriver(appiumDriver);
            return appiumDriver;

        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Appium Server URL: " + e.getMessage());
        }
    }

    private static AndroidDriver createAndroidDriver(URL serverUrl) {
        UiAutomator2Options options = new UiAutomator2Options();
        
        File appFile = new File(ConfigReader.getAndroidAppPath());
        if (appFile.exists()) {
            options.setApp(appFile.getAbsolutePath());
        } else {
            System.out.println("App file not found: " + appFile.getAbsolutePath());
            options.setAppPackage(ConfigReader.getAndroidAppPackage());
            options.setAppActivity(ConfigReader.getAndroidAppActivity());
        }
        
        options.setDeviceName(ConfigReader.getAndroidDeviceName());
        options.setPlatformVersion(ConfigReader.getAndroidPlatformVersion());
        options.setAutomationName(ConfigReader.getAndroidAutomationName());
        options.setNoReset(false);
        options.setFullReset(false);

        return new AndroidDriver(serverUrl, options);
    }

    private static IOSDriver createIOSDriver(URL serverUrl) {
        XCUITestOptions options = new XCUITestOptions();
        
        File appFile = new File(ConfigReader.getIosAppPath());
        if (appFile.exists()) {
            options.setApp(appFile.getAbsolutePath());
        } else {
            System.out.println("App file not found: " + appFile.getAbsolutePath());
            options.setBundleId(ConfigReader.getIosBundleId());
        }
        
        options.setDeviceName(ConfigReader.getIosDeviceName());
        options.setPlatformVersion(ConfigReader.getIosPlatformVersion());
        options.setAutomationName(ConfigReader.getIosAutomationName());
        options.setNoReset(false);
        options.setFullReset(false);

        return new IOSDriver(serverUrl, options);
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
