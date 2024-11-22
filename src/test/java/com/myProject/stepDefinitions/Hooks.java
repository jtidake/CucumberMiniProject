package com.myProject.stepDefinitions;

import com.aventstack.extentreports.Status;
import com.myProject.utilities.ConfigurationReader;
import com.myProject.utilities.Driver;
import com.myProject.utilities.ExtentReportManager;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


import java.util.concurrent.TimeUnit;

public class Hooks {

    @Before(order = 0)
    public void setUp(Scenario scenario) {
        // Log scenario start in Extent Report
        ExtentReportManager.createTest(scenario.getName())
                .log(Status.INFO, "Starting Scenario: " + scenario.getName());

        // Browser setup
        Driver.get().get(ConfigurationReader.get("url"));
        Driver.get().manage().window().maximize();
        Driver.get().manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
    }

    @After(order = 0)
    public void tearDown(Scenario scenario) {
        // Take screenshot if the scenario fails
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) Driver.get()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Screenshot for Failed Scenario");

            // Log failure in Extent Report
            ExtentReportManager.createTest(scenario.getName())
                    .log(Status.FAIL, "Scenario Failed: " + scenario.getName());
        } else {
            // Log success in Extent Report
            ExtentReportManager.createTest(scenario.getName())
                    .log(Status.PASS, "Scenario Passed: " + scenario.getName());
        }

        // Flush Extent Report and close the driver
        ExtentReportManager.flushReport();
        Driver.closeDriver();
    }
}
