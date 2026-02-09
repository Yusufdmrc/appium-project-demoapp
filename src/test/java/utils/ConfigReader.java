package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;
    private static String configFilePath;

    static {
        // Determine which config file to load based on system property or default
        String platform = System.getProperty("platform", "android");
        
        if (platform.equalsIgnoreCase("ios")) {
            configFilePath = "src/test/resources/config/config-ios.properties";
        } else {
            configFilePath = "src/test/resources/config/config-android.properties";
        }
        
        System.out.println("Loading configuration from: " + configFilePath);
        
        try {
            FileInputStream fis = new FileInputStream(configFilePath);
            properties = new Properties();
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config file: " + configFilePath);
        }
    }

    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Property '" + key + "' not found in " + configFilePath);
        }
        return value;
    }

    public static String getPlatform() {
        String platform = System.getProperty("platform", "android");
        return platform.toLowerCase();
    }

    public static String getAppiumServerUrl() {
        return getProperty("appium.server.url");
    }

    public static String getAndroidAppPath() {
        return getProperty("android.app.path");
    }

    public static String getAndroidDeviceName() {
        return getProperty("android.device.name");
    }

    public static String getAndroidPlatformVersion() {
        return getProperty("android.platform.version");
    }

    public static String getAndroidAutomationName() {
        return getProperty("android.automation.name");
    }

    public static String getAndroidAppPackage() {
        return getProperty("android.app.package");
    }

    public static String getAndroidAppActivity() {
        return getProperty("android.app.activity");
    }

    public static String getIosAppPath() {
        return getProperty("ios.app.path");
    }

    public static String getIosDeviceName() {
        return getProperty("ios.device.name");
    }

    public static String getIosPlatformVersion() {
        return getProperty("ios.platform.version");
    }

    public static String getIosAutomationName() {
        return getProperty("ios.automation.name");
    }

    public static String getIosBundleId() {
        return getProperty("ios.bundle.id");
    }

    public static String getValidUsername() {
        return getProperty("valid.username");
    }

    public static String getValidPassword() {
        return getProperty("valid.password");
    }

    public static int getImplicitWait() {
        return Integer.parseInt(getProperty("implicit.wait"));
    }

    public static int getExplicitWait() {
        return Integer.parseInt(getProperty("explicit.wait"));
    }

    public static boolean isScreenshotEnabled() {
        return Boolean.parseBoolean(getProperty("screenshot.enabled"));
    }

    public static String getScreenshotPath() {
        return getProperty("screenshot.path");
    }
}
