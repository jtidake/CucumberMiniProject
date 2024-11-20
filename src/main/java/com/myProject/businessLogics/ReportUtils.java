package com.myProject.businessLogics;

import io.qameta.allure.Allure;
import org.testng.Assert;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReportUtils {

    /**
     * Attaches a text file to the Allure report.
     * @param filePath The path of the file to be attached.
     * @param attachmentName The name of the attachment in the Allure report.
     */
    public static void attachTextFileToReport(String filePath, String attachmentName) {
        if (isFileValid(filePath)) {
            try (InputStream inputStream = new FileInputStream(filePath)) {
                Allure.addAttachment(attachmentName, inputStream);
            } catch (IOException e) {
                e.printStackTrace();
                Assert.fail("Failed to attach file to the report: " + e.getMessage());
            }
        } else {
            Assert.fail("Attachment file not found: " + filePath);
        }
    }

    /**
     * Helper method to check if the file exists and is readable.
     * @param filePath The path of the file to check.
     * @return boolean indicating if the file is valid.
     */
    private static boolean isFileValid(String filePath) {
        return Files.exists(Paths.get(filePath)) && Files.isReadable(Paths.get(filePath));
    }
}
