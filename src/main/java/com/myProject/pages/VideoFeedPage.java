package com.myProject.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class VideoFeedPage extends BasePage{
	
	@FindBy(xpath = "//a[@class=' _link_ob2qz_1 text-2sm']")
	public WebElement menuItem;
    
    @FindBy(xpath = "//a[text()='News & Features']")
    public WebElement newsAndFeaturesLink;
  
    @FindBy(xpath = "//h3[text()= 'NEWS']")
    public WebElement newsLogo;
    
    @FindBy(xpath = "//a[@class= 'TileArticle_tileLink__dBHYH']")
    public List<WebElement> videoElements;

    @FindBy(xpath = "//time[@aria-label and number(substring-before(@aria-label, ' days ago')) >= 3]")
    public List<WebElement> videoDates;    
    
}
