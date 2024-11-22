package com.myProject.stepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.myProject.pages.VideoFeedPage;
import com.myProject.utilities.BrowserUtils;
import com.myProject.utilities.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import static org.junit.Assert.assertTrue;
import org.junit.Assert;

public class VideoFeedSteps {
    
    VideoFeedPage videoFeedPage = new VideoFeedPage();
    private static final Logger logger = LogManager.getLogger(VideoFeedSteps.class);

    @Given("I navigate to the Warriors website")
    public void iNavigateToTheWarriorsWebsite() {
        String title = Driver.get().getTitle();        
        Assert.assertEquals("Golden State Warriors", title);
        logger.info("Successfully navigated to the Warriors website.");
    }

    @When("I hover over the menu item and click on News & Features")
    public void iHoverOverTheMenuItemAndClickOnNewsAndFeatures() {
        logger.info("Hovering over the menu item and clicking on News & Features.");
        videoFeedPage.clickOnMenuItem();
        videoFeedPage.clickOnNewsAndFeaturesLink();
        Assert.assertEquals("https://www.nba.com/warriors/news", Driver.get().getCurrentUrl());
        logger.info("Successfully clicked on News & Features and navigated to the correct page.");
    }

    @Then("I should be able to count all video feeds on the page")
    public void iShouldBeAbleToCountAllVideoFeeds() {
        BrowserUtils.waitForElementsToBeVisible(videoFeedPage.getVideoElements(), 20);        
        int totalFeeds = videoFeedPage.countVideoFeeds();
        logger.info("Total Video Feeds: " + totalFeeds);
        assertTrue("There are no video feeds", totalFeeds > 0);
    }

    @Then("I should count video feeds that are greater than or equal to three days old")
    public void iShouldCountVideoFeedsThatAreGreaterThanOrEqualToThreeDaysOld() {
        int feedsOverThreeDays = videoFeedPage.countVideoFeedsOlderThanThreeDays();
        logger.info("Video Feeds greater than or equal to 3 days old: " + feedsOverThreeDays);
        assertTrue("There are no video feeds older than 3 days", feedsOverThreeDays > 0);
    }
}
