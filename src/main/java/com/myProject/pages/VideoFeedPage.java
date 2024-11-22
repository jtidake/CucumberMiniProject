package com.myProject.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.myProject.utilities.BrowserUtils;
import java.util.List;

public class VideoFeedPage extends BasePage {
    
    @FindBy(xpath = "//a[@class=' _link_ob2qz_1 text-2sm']")
    private WebElement menuItem;
    
    @FindBy(xpath = "//a[text()='News & Features']")
    private WebElement newsAndFeaturesLink;
  
    @FindBy(xpath = "//h3[text()='NEWS']")
    private WebElement newsLogo;
    
    @FindBy(xpath = "//a[@class='TileArticle_tileLink__dBHYH']")
    private List<WebElement> videoElements;

    @FindBy(xpath = "//time[@aria-label]")
    private List<WebElement> videoDates;

    // Getter Methods for accessing elements
    public WebElement getMenuItem() {
        return menuItem;
    }

    public WebElement getNewsAndFeaturesLink() {
        return newsAndFeaturesLink;
    }

    public List<WebElement> getVideoElements() {
        return videoElements;
    }

    public List<WebElement> getVideoDates() {
        return videoDates;
    }

    // Helper Methods for interactions
    public void clickOnMenuItem() {
        BrowserUtils.hover(menuItem);
    }

    public void clickOnNewsAndFeaturesLink() {
        BrowserUtils.clickWithJS(newsAndFeaturesLink);
    }

    public int countVideoFeeds() {
        return videoElements.size();
    }

    public int countVideoFeedsOlderThanThreeDays() {
        int feedsOverThreeDays = 0;
        for (WebElement videoDate : videoDates) {
            // Calculate the date and compare it
            String dateText = videoDate.getAttribute("aria-label");
            if (isOlderThanThreeDays(dateText)) {
                feedsOverThreeDays++;
            }
        }
        return feedsOverThreeDays;
    }

    private boolean isOlderThanThreeDays(String dateText) {
        // Parse the date logic here, for now, we'll assume it has 'days ago'
        int daysAgo = Integer.parseInt(dateText.split(" ")[0]);
        return daysAgo >= 3;
    }
}
