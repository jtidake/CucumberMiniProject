package com.myProject.stepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.myProject.pages.FooterPage;
import com.myProject.utilities.BrowserUtils;
import com.myProject.utilities.Driver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;

import java.io.FileWriter;
import java.io.IOException;

public class FooterLink_StepDef {

	FooterPage footerPage = new FooterPage();
	private static final Logger logger = LogManager.getLogger(ShopPage_StepDefs.class);
	
	@Given("I am on the DP2 home page")
	public void iAmOnTheDP2HomePage() {
		Driver.get().get("https://www.nba.com/bulls/"); 
    	Assert.assertEquals("Bulls - The official site of the NBA for the latest NBA Scores, Stats & News. | NBA.com", Driver.get().getTitle());
	}
        
    @When("I scroll down to the footer section")
    public void i_scroll_down_to_the_footer_section() {
    	BrowserUtils.scrollToElement(footerPage.teamFooter);
    }   
    
   
    @When("I collect all the hyperlinks in the footer")
    public void i_collect_all_the_hyperlinks_in_the_footer() throws IOException {
    	int totalFeeds = footerPage.footerLinks.size();
        logger.info("Total Footer Links: " + totalFeeds);        
        assertTrue("No links found in the footer", totalFeeds > 0);
        
        List<WebElement> links = footerPage.footerLinks;
        Set<String> uniqueLinks = new HashSet<>();
        for (WebElement link : links) {
            String href = link.getAttribute("href");
            if (href != null && !href.isEmpty()) {
                uniqueLinks.add(href);
            }
        }

        // Save links to CSV
        try (FileWriter writer = new FileWriter("Output/footer_links.csv")) {
            for (String link : uniqueLinks) {
                writer.append(link);
                writer.append('\n');
            }
        }
    }
    
    @Then("I should save the hyperlinks into a CSV file")
    public void i_should_save_the_hyperlinks_into_a_csv_file() {
        // This step is already covered in the previous step where we save hyperlinks to CSV.
    }

    // And: I should check if any duplicate hyperlinks are present
    @Then("I should check if any duplicate hyperlinks are present")
    public void i_should_check_if_any_duplicate_hyperlinks_are_present() throws IOException {
        List<String> linksFromFile = java.nio.file.Files.readAllLines(java.nio.file.Paths.get("Output/footer_links.csv"));
        Set<String> linkSet = new HashSet<>(linksFromFile);
        if (linkSet.size() != linksFromFile.size()) {
            throw new AssertionError("There are duplicate links in the CSV file");
        }
    }

    // Then: I should report the duplicates if found
    @Then("I should report the duplicates if found")
    public void i_should_report_the_duplicates_if_found() throws IOException {
        List<String> linksFromFile = java.nio.file.Files.readAllLines(java.nio.file.Paths.get("Output/footer_links.csv"));
        Set<String> uniqueLinks = new HashSet<>();
        Set<String> duplicates = new HashSet<>();

        for (String link : linksFromFile) {
            if (!uniqueLinks.add(link)) {
                duplicates.add(link);
            }
        }

        if (!duplicates.isEmpty()) {
        	logger.info("Duplicate links found: " + duplicates);
        } else {
        	logger.info("No duplicate links found.");
        }
    }

    
}
