package com.myProject.stepDefinitions;

import com.myProject.pages.ProductPage;
import com.myProject.utilities.Driver;
import com.myProject.utilities.ReportUtils;
import io.cucumber.java.en.*;
import org.junit.Assert;

import java.io.IOException;

public class ShopPage_StepDefs {
    ProductPage productsPage = new ProductPage();
    
    @Given("The user is on the landing page")
    public void userIsOnHomePage() {
        Assert.assertEquals("Golden State Warriors", Driver.get().getTitle());
    }

    @When("The user clicks on the Men category in the Shop Page")
    public void userClicksOnMenCategoryInShopPage() {
        productsPage.clickOnShopMenu();
    }

    @Then("Verify user is navigated to Shop page successfully")
    public void verifyUserIsOnShopPage() {
        productsPage.switchToNewWindow();
        Assert.assertEquals("Golden State Warriors Shop, GSW Apparel, Warriors Jerseys | shop.warriors.com", Driver.get().getTitle());
    }

    @When("The user clicks on Men category")
    public void userClicksOnMenCategory() {
        productsPage.clickOnMenCategory();
    }

    @And("The user clicks on Jackets link under Men category")
    public void userClicksOnJacketsLink() {
        productsPage.clickOnJacketsLink();
    }

    @Then("Verify that category page is displayed and confirm text Men Jacket products")
    public void verifyMenJacketsPage() {
        productsPage.verifyCategoryHeader();
        Assert.assertEquals("Jackets", productsPage.getCategoryHeaderText());
    }

    @And("User should store the Jacket Price, Title, and Top Seller message in a text file")
    public void storeJacketDetailsInTextFile() throws IOException {
        productsPage.writeJacketDetailsToFile("Output/jacketDetails.txt");
    }

    @And("User should attach the text file to the report")
    public void attachTextFileToReport() {
        ReportUtils.attachTextFileToReport("Output/jacketDetails.txt", "Jacket Details");
    }
}
