package com.myProject.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.myProject.businessLogics.JacketDetailsWriter;
import com.myProject.utilities.BrowserUtils;
import com.myProject.utilities.Driver;

import java.io.IOException;
import java.util.List;

public class ProductPage extends BasePage {   
    
    // Locators for the Shop page     
    @FindBy(xpath = "//img[@alt=\"NBA Logo\"]")
    private WebElement logo;

    @FindBy(xpath = "//div[@class='p-2 absolute right-3 hover:cursor-pointer']")
    private WebElement closeButton;

    @FindBy(xpath = "//a[@rel='noreferrer']/span[text()='Shop']")
    private WebElement shopMenu;

    @FindBy(xpath = "//a[@aria-label=\"Men\"]")
    private WebElement menCategory;

    @FindBy(xpath = "//a[@href='/golden-state-warriors-men-jackets/t-25690618+ga-01+d-3438843071+z-9-4167543?_ref=m-TOPNAV']")
    private WebElement jacketsLink;

    @FindBy(xpath = "//a[@aria-label='Remove Jackets filter']")
    private WebElement categoryHeader;

    // Locators for Jackets page
    @FindBy(css = ".product-card")
    private List<WebElement> productCard;

    @FindBy(xpath = "//a[@target='_self' and starts-with(@title, \"Men's\")]")
    private List<WebElement> jacketTitles;

    @FindBy(xpath = "//span[@class='price primary']//span[contains(@class, 'money-value')]//span[@aria-hidden='true']")
    private List<WebElement> jacketPrices;

    @FindBy(xpath = "//div[@class='product-vibrancy-container']//span[contains(text(), 'Most Popular')]")
    private List<WebElement> topSellers;

    @FindBy(xpath = "(//i[@class='icon icon-right-arrow'])[2]")
    private WebElement getNextPageButton;

    // Encapsulated interaction methods

    public void clickOnShopMenu() {
        BrowserUtils.clickWithJS(shopMenu);
    }

    public void clickOnMenCategory() {
        BrowserUtils.clickWithJS(menCategory);
    }

    public void clickOnJacketsLink() {
        BrowserUtils.clickWithJS(jacketsLink);
    }

    public void verifyCategoryHeader() {
        BrowserUtils.verifyElementDisplayed(categoryHeader);
    }

    public String getCategoryHeaderText() {
        return categoryHeader.getText();
    }

    public List<WebElement> getProductCards() {
        return productCard;
    }

    public List<WebElement> getJacketTitles() {
        return jacketTitles;
    }

    public List<WebElement> getJacketPrices() {
        return jacketPrices;
    }

    public List<WebElement> getTopSellerMessages() {
        return topSellers;
    }

    public void switchToNewWindow() {
        BrowserUtils.switchToNewWindow(Driver.get());
    }

    public void writeJacketDetailsToFile(String filePath) throws IOException {
        List<WebElement> jacketTitles = getJacketTitles();
        List<WebElement> jacketPrices = getJacketPrices();
        List<WebElement> topSellerMessages = getTopSellerMessages();
        
        JacketDetailsWriter jacketDetailsWriter = new JacketDetailsWriter();
        jacketDetailsWriter.writeJacketDetailsToFile(getProductCards(), jacketTitles, jacketPrices, topSellerMessages, filePath);
    }
}
