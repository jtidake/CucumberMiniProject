package com.myProject.stepDefinitions;

import com.myProject.businessLogics.JacketDetailsWriter;
import com.myProject.businessLogics.ReportUtils;
import com.myProject.pages.ProductPage;
import com.myProject.utilities.BrowserUtils;
import com.myProject.utilities.Driver;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import java.io.*;
import java.util.List;


public class ShopPage_StepDefs {
    ProductPage productsPage = new ProductPage();
    JacketDetailsWriter jacketDetailsWriter = new JacketDetailsWriter();
    
    @Given("The user is on the landing page")
    public void userIsOnHomePage() {
        String title = Driver.get().getTitle();        
        Assert.assertEquals("Golden State Warriors", title);
    }

    @When("The user clicks on the Men category in the Shop Page")
    public void userClicksOnMenCategoryInShopPage() {
        BrowserUtils.clickWithJS(productsPage.shopMenu);
    }

    @Then("Verify user is navigated to Shop page successfully")
    public void verifyUserIsOnShopPage() {
    	BrowserUtils.switchToNewWindow(Driver.get());        
        Assert.assertEquals("Golden State Warriors Shop, GSW Apparel, Warriors Jerseys | shop.warriors.com", Driver.get().getTitle());
    }

    @When("The user clicks on Men category")
    public void userClicksOnMenCategory() {
        BrowserUtils.clickWithJS(productsPage.menCategory);
    }

    @And("The user clicks on Jackets link under Men category")
    public void userClicksOnJacketsLink() {
        BrowserUtils.clickWithJS(productsPage.jacketsLink);
    }

    @Then("Verify that category page is displayed and confirm text Men Jacket products")
    public void verifyMenJacketsPage() {
        WebElement categoryHeader = productsPage.categoryHeader;
        BrowserUtils.verifyElementDisplayed(categoryHeader);
        Assert.assertEquals("Jackets", categoryHeader.getText());
    }

    @And("User should store the Jacket Price, Title, and Top Seller message in a text file")
    public void storeJacketDetailsInTextFile() throws IOException {
    	List<WebElement> productCard = productsPage.productCard;
    	List<WebElement> jacketTitles = productsPage.jacketTitles;
        List<WebElement> jacketPrices = productsPage.jacketPrices;
        List<WebElement> topSellerMessages = productsPage.topSellers;

        // Call the business logic class method to write details to a file
        jacketDetailsWriter.writeJacketDetailsToFile(productCard, jacketTitles, jacketPrices, topSellerMessages, "Output/jacketDetails.txt");
    }

    @And("User should attach the text file to the report")
    public void attachTextFileToReport() {
    	ReportUtils.attachTextFileToReport("Output/jacketDetails.txt", "Jacket Details");
    }
}
