package runners;

import org.testng.annotations.BeforeClass;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepdefinitions", "utils"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "timeline:target/cucumber-reports/timeline"
        },
        monochrome = true,
        dryRun = false,
        tags = "@negative"
)
public class TestRunner extends AbstractTestNGCucumberTests {
    
    @BeforeClass
    public void setup() {
        String platform = System.getProperty("platform", "android");
        System.out.println("========================================");
        System.out.println("Running tests on: " + platform.toUpperCase());
        System.out.println("========================================");
    }
}
