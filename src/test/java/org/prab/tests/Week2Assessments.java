package org.prab.tests;

import org.prab.fw.TestBase;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.prab.pages.Week2DisabledInputPage;
import org.prab.pages.Week2TagsPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class Week2Assessments extends TestBase {
    @Test(priority = 1)
    public void disabledInputTest() {
        String firstName = "Jack";
        String password = "Innova";
        driver.navigate().to("https://seleniumpractise.blogspot.com/2016/09/how-to-work-with-disable-textbox-or.html");
        Week2DisabledInputPage week2DisabledInputPage = new Week2DisabledInputPage(driver);

        // Enter All Inputs
        week2DisabledInputPage.enterFirstName(firstName);
        week2DisabledInputPage.enterPassword(password);
        week2DisabledInputPage.enterNewPassword(password);

        // Validate All inputs
        assertEquals(week2DisabledInputPage.getFirstName(), firstName, "Entered First Name is incorrect");
        assertEquals(week2DisabledInputPage.getPassword(), password, "Entered First Name is incorrect");
        assertEquals(week2DisabledInputPage.getNewPassword(), password, "Entered First Name is incorrect");
    }

    @Test(priority = 2)
    public void tagsTest() {
        String[] tagsList = {"Innova1", "Innova2", "Innova3", "Innova4", "Innova5", "Innova6", "Innova7", "Innova8", "Innova9", "Innova10"};

        driver.navigate().to("https://qaplayground.dev/apps/tags-input-box/");

        Week2TagsPage tagsPage = new Week2TagsPage(driver);

        // Initial Tags Validation
        ArrayList<String> initialTagsList = tagsPage.getSelectedTagsAsValue();
        int initialTagsCount = initialTagsList.size();
        System.out.println("Initial Tags Count: " + initialTagsCount);
        assertEquals(initialTagsCount, 2, "Initial Tags count is incorrect");
        System.out.println("Initial Assigned Tags List: " + Arrays.toString(initialTagsList.toArray()));

        // Remove All Tags Validation
        tagsPage.clickRemoveAllBtn();
        int tagsAfterRemoveAll = tagsPage.getSelectedTagsAsValue().size();
        System.out.println("After Remove All Tags Count: " + tagsAfterRemoveAll);
        assertEquals(tagsAfterRemoveAll, 0, "After Remove All Tags count is incorrect");

        // Assign 10 Tags and validate
        tagsPage.enterTags(tagsList);
        ArrayList<String> currentTagsList = tagsPage.getSelectedTagsAsValue();
        int currentTagsCount = currentTagsList.size();
        System.out.println("Current Tags Count: " + currentTagsCount);
        assertEquals(currentTagsCount, 10, "Current Tags count is incorrect");
        System.out.println("Current Assigned Tags List: " + Arrays.toString(currentTagsList.toArray()));

        //<script> alert() </script> validation
        tagsPage.clickRemoveAllBtn();
        tagsPage.enterTags("<script> alert() </script>");
        List<WebElement> finalTagsList = tagsPage.getSelectedTagsAsElements();
        int finalTagsCount = finalTagsList.size();
        System.out.println("Final Tags Count: " + finalTagsCount);
        assertEquals(finalTagsCount, 1, "Final Tags count is incorrect");
        WebElement element = finalTagsList.get(0);
        System.out.println("Final Tag html value in node <li>: " + element.getAttribute("innerHTML"));
        System.out.println("Final Tag value in node <li>: " + element.getAttribute("textContent"));
    }
}