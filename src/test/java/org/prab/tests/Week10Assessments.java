package org.prab.tests;

import org.prab.fw.TestBase;
import org.testng.annotations.Test;
import org.prab.pages.Week10ProgressBarPage;
import org.prab.pages.Week10RedirectPage;
import org.prab.pages.Week10ScrollPage;

import static org.testng.Assert.assertEquals;

public class Week10Assessments extends TestBase {
    @Test(priority = 2)
    public void progressBarTest() {
        driver.navigate().to("http://uitestingplayground.com/progressbar");
        Week10ProgressBarPage progressBarPage = new Week10ProgressBarPage(driver);

        // Start the progress
        progressBarPage.clickStartButton();
        // Wait and stop the progress
        progressBarPage.waitForProgressBar(65);
    }

    @Test(priority = 3)
    public void scrollAndClickTest() {
        driver.navigate().to("https://qaplayground.dev/apps/covered");
        Week10ScrollPage scrollPage = new Week10ScrollPage(driver);

        System.out.println("Initial Message: " + scrollPage.getInfoMessage());
        assertEquals(scrollPage.getInfoMessage(), "Click the button below", "Initial message is incorrect.");
        scrollPage.clickYouFoundMe();
        System.out.println("Final Message: " + scrollPage.getInfoMessage());
        assertEquals(scrollPage.getInfoMessage(), "Mission accomplished", "Final message is incorrect.");
    }

    @Test(priority = 1)
    public void redirectTest() {
        driver.manage().deleteAllCookies();
        String[] pages = {"Second", "Third", "Fourth", "Fifth", "Sixth"};
        driver.navigate().to("https://qaplayground.dev/apps/redirect");
        Week10RedirectPage redirectPage = new Week10RedirectPage(driver);
        redirectPage.validatePages(pages);
    }
}