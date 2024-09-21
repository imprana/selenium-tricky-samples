package org.prab.tests;

import org.openqa.selenium.WebElement;
import org.prab.fw.TestBase;
import org.prab.pages.Week1HerokuAppPage;
import org.prab.pages.Week1MaximumLinksPage;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.assertEquals;

public class Week1Assessments extends TestBase {
    /*
    Develop an automation script to login to below app through Browser Authentication Popup.
    Link: https://the-internet.herokuapp.com/basic_auth
    Username: admin
    Password: admin
    */
    @Test(priority = 1)
    public void basicAuthTest() {
        // Login the app using Basic HTTP Authentication
        driver.navigate().to("https://admin:admin@the-internet.herokuapp.com/basic_auth");
        Week1HerokuAppPage herokuAppPage = new Week1HerokuAppPage(driver);
        assertEquals(herokuAppPage.getAuthSuccessTitle(), "Basic Auth", "Auth success title is incorrect");
        assertEquals(herokuAppPage.getAuthSuccessContent(), "Congratulations! You must have the proper credentials.", "Auth success content is incorrect");
    }

    /*
    1) 𝐍𝐚𝐯𝐢𝐠𝐚𝐭𝐞 𝐭𝐨 4 𝐰𝐞𝐛𝐬𝐢𝐭𝐞 𝐨𝐧𝐞 𝐛𝐲 𝐨𝐧𝐞 𝐰𝐡𝐢𝐜𝐡 𝐢𝐬 𝐦𝐞𝐧𝐭𝐢𝐨𝐧𝐞𝐝 𝐛𝐞𝐥𝐨𝐰 :
        - https://www.lambdatest.com/blog/selenium-best-practices-for-web-testing/
        - https://www.ministryoftesting.com/articles/websites-to-practice-testing
        - https://naveenautomationlabs.com/opencart/
        - https://demo.guru99.com/
    2) 𝐬𝐭𝐨𝐫𝐞 below info for 𝐚𝐥𝐥 4 𝐔𝐑𝐋𝐬 𝐢𝐧 𝐒𝐭𝐫𝐢𝐧𝐠 𝐀𝐫𝐫𝐚𝐲 𝐬𝐞𝐭.
    𝐔𝐫𝐥 𝐨𝐟 𝐏𝐚𝐠𝐞,𝐏𝐚𝐠𝐞 𝐓𝐢𝐭𝐥𝐞 & 𝐍𝐮𝐦𝐛𝐞𝐫 𝐨𝐟 𝐥𝐢𝐧𝐤𝐬 𝐨𝐧 𝐭𝐡𝐚𝐭 𝐬𝐩𝐞𝐜𝐢𝐟𝐢𝐞𝐝 𝐔𝐑𝐋 𝐏𝐚𝐠𝐞(𝐇𝐨𝐦𝐞 𝐏𝐚𝐠𝐞).
    3) 𝐝𝐢𝐬𝐩𝐥𝐚𝐲 the output 𝐢𝐧 𝐭𝐡𝐢𝐬 𝐟𝐨𝐫𝐦: "𝐏𝐚𝐠𝐞 𝐰𝐢𝐭𝐡 𝐌𝐚𝐱𝐢𝐦𝐮𝐦 𝐋𝐢𝐧𝐤𝐬: [𝐏𝐚𝐠𝐞 𝐓𝐢𝐭𝐥𝐞] - [𝐍𝐮𝐦𝐛𝐞𝐫 𝐨𝐟 𝐋𝐢𝐧𝐤𝐬] 𝐥𝐢𝐧𝐤𝐬".
    */
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