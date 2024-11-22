package com.myProject.runners;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        plugin = {
                "json:target/cucumber.json",
                "html:target/default-html-reports.html"
        },
        features = "src/test/resources/features",
        glue = "com/myProject/stepDefinitions",
        dryRun = false,
        tags = "@smoke",
        monochrome=true
)
public class CukesRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true) // Set parallel to true for parallel execution (optional)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
