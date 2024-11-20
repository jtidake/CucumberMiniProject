package com.myProject.stepDefinitions;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.myProject.utilities.ConfigurationReader;
import com.myProject.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.concurrent.TimeUnit;

public class Hooks {
    @BeforeMethod
	@Before(order = 0)
    public void setUp() {
    	Driver.get().get(ConfigurationReader.get("url"));
        Driver.get().manage().window().maximize();
        Driver.get().manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
    }

    @AfterMethod
	@After(order = 0)
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) Driver.get()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "screenshot");
        }
        Driver.closeDriver();
    }

}
