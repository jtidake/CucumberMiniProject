package com.myProject.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.myProject.utilities.Driver;

import java.util.List;

public class ProductPage extends BasePage{   
    
    // Locators for the Shop page     
    @FindBy(xpath = "//img[@alt=\"NBA Logo\"]")
    public WebElement logo;
    
    @FindBy(xpath = "//div[@class='p-2 absolute right-3 hover:cursor-pointer']")
    public WebElement closeButton;
    
    @FindBy(xpath = "//a[@rel='noreferrer']/span[text()='Shop']")
    public WebElement shopMenu;

    @FindBy(xpath = "//a[@aria-label=\"Men\"]")
    public WebElement menCategory;

    @FindBy(xpath = "//a[@href='/golden-state-warriors-men-jackets/t-25690618+ga-01+d-3438843071+z-9-4167543?_ref=m-TOPNAV']")
    public WebElement jacketsLink;

    @FindBy(xpath = "//a[@aria-label='Remove Jackets filter']")
    public WebElement categoryHeader;

    // Locators for Jackets page
    @FindBy(css = ".product-card")
    public List<WebElement> productCard;
    
    @FindBy(xpath = "//a[@target='_self' and starts-with(@title, \"Men's\")]") 
    public List<WebElement> jacketTitles;

    @FindBy(xpath = "//span[@class='price primary']//span[contains(@class, 'money-value')]//span[@aria-hidden='true']") 
    public List<WebElement> jacketPrices;

    @FindBy(xpath = "//div[@class='product-vibrancy-container']//span[contains(text(), 'Most Popular')]") 
    public List<WebElement> topSellers;
    
    @FindBy(xpath = "(//i[@class='icon icon-right-arrow'])[2]") 
    public WebElement getNextPageButton;
    
}
