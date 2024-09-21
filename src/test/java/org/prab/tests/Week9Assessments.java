package org.prab.tests;

import org.prab.fw.TestBase;
import org.prab.pages.Week9DownloadPage;
import org.prab.pages.Week9MouseHoverPage;
import org.prab.utils.FileOperations;
import org.testng.annotations.Test;

import java.io.File;
import java.text.DecimalFormat;

import static org.testng.Assert.assertTrue;

public class Week9Assessments extends TestBase {
    /*
    Navigate to https://qaplayground.dev/apps/mouse-hover/
    Hover the mouse on the movie poster
    Display the price
    */
    @Test(priority = 1)
    public void hoverTest() {
        driver.navigate().to("https://qaplayground.dev/apps/mouse-hover/");
        Week9MouseHoverPage mouseHoverPage = new Week9MouseHoverPage(driver);

        // Do mouse hover
        mouseHoverPage.doMouseHoverOnThePoster();
        assertTrue(mouseHoverPage.isPosterDetailsDisplayed(), "The poster content does not appear when the mouse hovers over it.");
        // Print the price details
        mouseHoverPage.printPriceDetails();
    }

    /*
    Navigate to https://demo.automationtesting.in/FileDownload.html
    Click on Download button. A sample file is downloaded
    Print the filename, size, path of the file downloaded
    Verify the text “Get Tickets” is present in the file
    */
    @Test(priority = 2)
    public void downloadTest() throws Exception {
        // Delete the Previous files if any
        FileOperations.deleteFilesFromDir(downloadPath);

        String downloadFileName = "samplefile.pdf";
        driver.navigate().to("https://demo.automationtesting.in/FileDownload.html");
        Week9DownloadPage downloadPage = new Week9DownloadPage(driver);

        System.out.println("Download path: " + downloadPath);
        // Do download
        downloadPage.doDownload();
        assertTrue(FileOperations.validateFileDownload(downloadPath, downloadFileName, false), "File has not been downloaded.");

        // Print file details
        File file = FileOperations.getLatestFileFromDir(downloadPath);
        DecimalFormat df = new DecimalFormat("#.##");
        long fileSizeInBytes = file.length();
        String fileSizeInMB = df.format(fileSizeInBytes / (1024.0 * 1024.0));
        String filePath = file.getPath();

        System.out.println("File details: ");
        System.out.println("File name: " + file.getName());
        System.out.println("File size (bytes): " + fileSizeInBytes + " bytes");
        System.out.println("File size (MB): " + fileSizeInMB + " MB");
        System.out.println("File path: " + filePath);

        // Read validate the file content
        String pdfContent = FileOperations.getPDFContent(filePath);
        assertTrue(pdfContent.contains("Get Tickets"), "PDF content does not have the word 'Get Tickets'");
    }
}