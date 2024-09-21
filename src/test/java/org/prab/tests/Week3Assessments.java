package org.prab.tests;

import org.prab.fw.TestBase;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.prab.pages.Week3DownloadPage;
import org.prab.pages.Week3StarRatingsPage;
import org.prab.utils.FileOperations;

import static org.testng.Assert.assertEquals;

public class Week3Assessments extends TestBase {
    private String downloadFileName = "Selenium-Cheat-Sheet-2022.pdf";

    @BeforeTest
    public void deleteAllExistingFiles() {
        FileOperations.deleteFilesFromDir(downloadPath);
    }

    @Test(priority = 1)
    public void downloadTest() throws Exception {
        driver.navigate().to("https://intellipaat.com/blog/tutorial/selenium-tutorial/selenium-cheat-sheet/");
        Week3DownloadPage downloadPage = new Week3DownloadPage(driver);

        System.out.println("Download path: " + downloadPath);
        // Download the file
        downloadPage.clickDownloadLink();
        assertEquals(FileOperations.validateFileDownload(downloadPath, downloadFileName, true), true, "File is not downloaded");
    }

    @Test(priority = 2)
    public void checkStarRatingsTest() {
        driver.navigate().to("https://play1.automationcamp.ir/advanced.html");
        Week3StarRatingsPage starRatingsPage = new Week3StarRatingsPage(driver);

        // Download settings updated in driver initialization
        // Get Star Rating
        String starRating = starRatingsPage.getStarRating();
        System.out.println("Star Rating: " + starRating);

        // Validate Star Rating
        starRatingsPage.validateStarRating(starRating);
        assertEquals(starRatingsPage.getSuccessMessage(), "Well done!", "Success Message is incorrect");
    }
}