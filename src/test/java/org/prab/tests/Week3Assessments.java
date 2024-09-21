package org.prab.tests;

import org.prab.fw.TestBase;
import org.prab.pages.Week3DownloadPage;
import org.prab.pages.Week3StarRatingsPage;
import org.prab.utils.FileOperations;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Week3Assessments extends TestBase {

    @BeforeTest
    public void deleteAllExistingFiles() {
        FileOperations.deleteFilesFromDir(downloadPath);
    }

    /*
    Use CSS Pseudo elements
    Navigate to https://play1.automationcamp.ir/advanced.html
    Read * rating of the book,
    Enter the stars in text box and click "Check rating" button,
    you should see "well done!" message. Verify the message
    */
    @Test(priority = 1)
    public void downloadTest() throws Exception {
        driver.navigate().to("https://intellipaat.com/blog/tutorial/selenium-tutorial/selenium-cheat-sheet/");
        Week3DownloadPage downloadPage = new Week3DownloadPage(driver);

        System.out.println("Download path: " + downloadPath);
        // Download the file
        downloadPage.clickDownloadLink();
        String downloadFileName = "Selenium-Cheat-Sheet-2022.pdf";
        assertTrue(FileOperations.validateFileDownload(downloadPath, downloadFileName, true), "File is not downloaded");
    }

    /*
    Navigate to https://intellipaat.com/blog/tutorial/selenium-tutorial/selenium-cheat-sheet/
    Download the pdf file in to a specific folder
    Verify if downloaded file is pdf or not
    */
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