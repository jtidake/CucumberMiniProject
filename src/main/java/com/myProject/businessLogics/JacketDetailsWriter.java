package com.myProject.businessLogics;

import org.openqa.selenium.WebElement;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JacketDetailsWriter {

    // Method to write jacket details into a text file
	public void writeJacketDetailsToFile(List<WebElement> productCard,
			List<WebElement> jacketTitles, 
            List<WebElement> jacketPrices, 
            List<WebElement> topSellerMessages, 
            String filePath) throws IOException {

			// Get the minimum size among the lists to prevent IndexOutOfBoundsException
//			int size = Math.min(jacketTitles.size(), Math.min(jacketPrices.size(), topSellerMessages.size()));
		     int size = productCard.size();
		     
			// If the lists are of unequal sizes, handle missing elements with blanks
//			int maxSize = Math.max(jacketTitles.size(), Math.max(jacketPrices.size(), topSellerMessages.size()));
			System.out.println(topSellerMessages.size());
			
			File file = new File(filePath);
			try (FileWriter writer = new FileWriter(file)) {	
				for (int i = 0; i < size; i++) {
				// Get title, price, and top seller message or blank if out of bounds
				String title = (i < jacketTitles.size()) ? jacketTitles.get(i).getText() : "";
				String price = (i < jacketPrices.size()) ? jacketPrices.get(i).getText() : "";
				String topSeller = (i < topSellerMessages.size() && topSellerMessages.get(i).isDisplayed()) ? "Top Seller" : "";
				
				// Write the jacket details to the file
				writer.write("Title: " + title + "| Price: " + price + " | " + topSeller + "\n");
				}
			}
	}
}
