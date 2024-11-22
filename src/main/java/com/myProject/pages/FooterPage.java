package com.myProject.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.myProject.utilities.BrowserUtils;

import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class FooterPage extends BasePage {

    @FindBy(xpath = "//h2[text()='Team']")
    private WebElement teamFooter;

    @FindBy(xpath = "//a[@class='text-sm antialiased focus:text-white hover:text-white hover:underline']")
    private List<WebElement> footerLinks;

    @FindBy(xpath = "//h2[@class='text-base font-bold text-left pb-7.5 uppercase']")
    private List<WebElement> footers;

    // Scroll to the footer
    public void scrollToFooter() {
        BrowserUtils.scrollToElement(teamFooter);
    }

    // Collect all the unique links from the footer
    public Set<String> getFooterLinks() {
        Set<String> uniqueLinks = new HashSet<>();
        for (WebElement link : footerLinks) {
            String href = link.getAttribute("href");
            if (href != null && !href.isEmpty()) {
                uniqueLinks.add(href);
            }
        }
        return uniqueLinks;
    }

    // Check if there are duplicate links in the footer
    public Set<String> getDuplicateLinks(Set<String> linksFromFile) {
        Set<String> uniqueLinks = new HashSet<>();
        Set<String> duplicates = new HashSet<>();
        for (String link : linksFromFile) {
            if (!uniqueLinks.add(link)) {
                duplicates.add(link);
            }
        }
        return duplicates;
    }

    // Verify if footer links are present
    public boolean areFooterLinksPresent() {
        return footerLinks.size() > 0;
    }
}
