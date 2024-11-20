package com.myProject.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class FooterPage extends BasePage{
    
    @FindBy(xpath = "//h2[text()='Team']")
    public WebElement teamFooter;

    @FindBy(xpath = "//a[@class=\"text-sm antialiased focus:text-white hover:text-white hover:underline\"]")
    public List<WebElement> footerLinks; 
    
    @FindBy(xpath = "//h2[@class='text-base font-bold text-left pb-7.5 uppercase']")
    public List<WebElement> footers; 
  
       
}

