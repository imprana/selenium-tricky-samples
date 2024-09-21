package org.prab.tests;

import org.openqa.selenium.WebElement;
import org.prab.fw.TestBase;
import org.prab.pages.Week8DragAndSortPage;
import org.prab.pages.Week8EmojiRatingPage;
import org.testng.annotations.Test;

import java.util.List;

public class Week8Assessments extends TestBase {
    /*
    Navigate to https://qaplayground.dev/apps/rating/
    Set each available rating value and assert image, text and number
    Display text message e.g-"I don't like it" and the <number> out of 5
    */
    @Test(priority = 1)
    public void emojiRatingTest() {
        driver.navigate().to("https://qaplayground.dev/apps/rating/");
        Week8EmojiRatingPage emojiRatingPage = new Week8EmojiRatingPage(driver);

        List<WebElement> stars = emojiRatingPage.getStars();
        System.out.println("Starts count: " + stars.size());

        for (int i = 1; i <= stars.size(); i++) {
            emojiRatingPage.clickStars(i);
            emojiRatingPage.doRatingAssertions(i);
        }
    }

    /*
    Navigate to https://qaplayground.dev/apps/sortable-list/
    Here is the list of 10 richest people. Arrange them in the below order by drag and drop.

    𝐄𝐱𝐩𝐞𝐜𝐭𝐞𝐝 𝐨𝐫𝐝𝐞𝐫 𝐨𝐟 𝐧𝐚𝐦𝐞𝐬 𝐢𝐧 𝐋𝐢𝐬𝐭 𝐨𝐫𝐝𝐞𝐫:
    position: 1, name: "Jeff Bezos"
    position: 2, name: "Bill Gates"
    position: 3, name: "Warren Buffett"
    position: 4, name: "Bernard Arnault"
    position: 5, name: "Carlos Slim Helu"
    position: 6, name: "Amancio Ortega"
    position: 7, name: "Larry Ellison"
    position: 8, name: "Mark Zuckerberg"
    position: 9, name: "Michael Bloomberg"
    */
    @Test(priority = 2)
    public void sortingTest() throws Exception {
        String[] expectedList = {"Jeff Bezos", "Bill Gates", "Warren Buffett", "Bernard Arnault", "Carlos Slim Helu", "Amancio Ortega", "Larry Ellison", "Mark Zuckerberg", "Michael Bloomberg"};
        driver.navigate().to("https://qaplayground.dev/apps/sortable-list/");
        Week8DragAndSortPage dragAndSortPage = new Week8DragAndSortPage(driver);

        dragAndSortPage.doSorting(expectedList);
        dragAndSortPage.clickCheckOrderBtn();
        dragAndSortPage.doAssertionsForSortedList(expectedList);
    }
}