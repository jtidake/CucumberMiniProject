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
	private static final Logger logger = LogManager.getLogger(ShopPage_StepDefs.class);
    
    @Given("I navigate to the Warriors website")
    public void iNavigateToTheWarriorsWebsite() {    	
    	String title = Driver.get().getTitle();        
        Assert.assertEquals("Golden State Warriors", title);
    }

    @When("I hover over the menu item and click on News & Features")
    public void iHoverOverTheMenuItemAndClickOnNewsAndFeatures() {
//        videoFeedPage.hoverOverMenuItemAndClickNewsAndFeatures();
    	BrowserUtils.hover(videoFeedPage.menuItem);
    	BrowserUtils.clickWithJS(videoFeedPage.newsAndFeaturesLink);
    	Assert.assertEquals("https://www.nba.com/warriors/news", Driver.get().getCurrentUrl());
    	
    }

    @Then("I should be able to count all video feeds on the page")
    public void iShouldBeAbleToCountAllVideoFeeds() {
        int totalFeeds = videoFeedPage.videoElements.size();
        logger.info("Total Video Feeds: " + totalFeeds);
        assertTrue("There are no video feeds", totalFeeds > 0);
    }

    @Then("I should count video feeds that are greater than or equal to three days old")
    public void iShouldCountVideoFeedsThatAreGreaterThanOrEqualToThreeDaysOld() {
        int feedsOverThreeDays = videoFeedPage.videoDates.size();
        logger.info("Video Feeds greater than or equal to 3 days old: " + feedsOverThreeDays);
        assertTrue("There are no video feeds older than 3 days", feedsOverThreeDays > 0);
    }
}
