package org.prab.tests;

import org.prab.fw.TestBase;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.prab.pages.Week8DragAndSortPage;
import org.prab.pages.Week8EmojiRatingPage;

import java.util.List;

public class Week8Assessments extends TestBase {
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