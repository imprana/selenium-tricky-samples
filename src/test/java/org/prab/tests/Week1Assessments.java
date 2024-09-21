package org.prab.tests;

import org.prab.fw.TestBase;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.prab.pages.Week1HerokuAppPage;
import org.prab.pages.Week1MaximumLinksPage;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class Week1Assessments extends TestBase {
    @Test(priority = 1)
    public void basicAuthTest() {
        // Login the app using Basic HTTP Authentication
        driver.navigate().to("https://admin:admin@the-internet.herokuapp.com/basic_auth");
        Week1HerokuAppPage herokuAppPage = new Week1HerokuAppPage(driver);
        assertEquals(herokuAppPage.getAuthSuccessTitle(), "Basic Auth", "Auth success title is incorrect");
        assertEquals(herokuAppPage.getAuthSuccessContent(), "Congratulations! You must have the proper credentials.", "Auth success content is incorrect");
    }

    @Test(priority = 2)
    public void maximumLinksTest() {
        String[] pageURLList = {
                "https://www.lambdatest.com/blog/selenium-best-practices-for-web-testing/",
                "https://www.ministryoftesting.com/articles/websites-to-practice-testing",
                "https://demo.guru99.com/",
                "https://naveenautomationlabs.com/opencart/"
        };

        Week1MaximumLinksPage linksPage = new Week1MaximumLinksPage(driver);
        List<WebElement> links;
        Map<String, Integer> countMap = new HashMap<>();
        for (String url : pageURLList) {
            driver.navigate().to(url);
            links = linksPage.getAllLinksAsList();
            System.out.println("Page Title: " + driver.getTitle() + ", Number of Links: " + links.size());
            Set<String> page1Details = new HashSet<>();
            page1Details.add(driver.getCurrentUrl());
            page1Details.add(driver.getTitle());
            page1Details.add(String.valueOf(links.size()));
            countMap.put(driver.getTitle(), links.size());
        }

        Map.Entry<String, Integer> maxEntry = Collections.max(countMap.entrySet(),
                Map.Entry.comparingByValue());
        System.out.println("Page with Maximum Links: " + maxEntry.getKey() + " - " + maxEntry.getValue() + " links.");
    }
}