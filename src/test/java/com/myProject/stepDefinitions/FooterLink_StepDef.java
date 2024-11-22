package com.myProject.stepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import com.myProject.pages.FooterPage;
import com.myProject.utilities.Driver;
import com.myProject.utilities.FileUtils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.io.IOException;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class FooterLink_StepDef {

    FooterPage footerPage = new FooterPage();
    private static final Logger logger = LogManager.getLogger(FooterLink_StepDef.class);
    private static final String HOMEPAGE_URL = "https://www.nba.com/bulls/";
    private static final String EXPECTED_TITLE = "Bulls - The official site of the NBA for the latest NBA Scores, Stats & News. | NBA.com";

    @Given("I am on the DP2 home page")
    public void iAmOnTheDP2HomePage() {
        logger.info("Navigating to the homepage...");
        Driver.get().get(HOMEPAGE_URL);
        Assert.assertEquals("Validation failed: Title mismatch.", EXPECTED_TITLE, Driver.get().getTitle());
        logger.info("Validation home page successful");
    }

    @When("I scroll down to the footer section")
    public void i_scroll_down_to_the_footer_section() {
        footerPage.scrollToFooter();
    }

    @When("I collect all the hyperlinks in the footer")
    public void i_collect_all_the_hyperlinks_in_the_footer() throws IOException {
        Set<String> uniqueLinks = footerPage.getFooterLinks();
        logger.info("Total Footer Links: " + uniqueLinks.size());
        
        assertTrue("No links found in the footer", uniqueLinks.size() > 0);
        
        // Save the links to CSV
        FileUtils.writeToCSV("Output/footer_links.csv", uniqueLinks);
    }

    @Then("I should save the hyperlinks into a CSV file")
    public void i_should_save_the_hyperlinks_into_a_csv_file() {
        // This step is already covered by the previous method
    }

    @Then("I should check if any duplicate hyperlinks are present")
    public void i_should_check_if_any_duplicate_hyperlinks_are_present() throws IOException {
        Set<String> linksFromFile = FileUtils.readLinksFromCSV("Output/footer_links.csv");
        Set<String> duplicates = footerPage.getDuplicateLinks(linksFromFile);

        assertTrue("Duplicate links found in the CSV file", duplicates.isEmpty());
    }

    @Then("I should report the duplicates if found")
    public void i_should_report_the_duplicates_if_found() throws IOException {
        Set<String> linksFromFile = FileUtils.readLinksFromCSV("Output/footer_links.csv");
        Set<String> duplicates = footerPage.getDuplicateLinks(linksFromFile);

        if (!duplicates.isEmpty()) {
            logger.info("Duplicate links found: " + duplicates);
            throw new AssertionError("Duplicate links found in the footer: " + duplicates);
        } else {
            logger.info("No duplicate links found.");
        }
    }
}
