package org.prab.tests;

import org.openqa.selenium.WebElement;
import org.prab.fw.TestBase;
import org.prab.pages.Week2DisabledInputPage;
import org.prab.pages.Week2TagsPage;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class Week2Assessments extends TestBase {
    /*
    Develop an automation script that will input a value in a disabled field. Use this url
    https://seleniumpractise.blogspot.com/2016/09/how-to-work-with-disable-textbox-or.html
    */
    @Test(priority = 1)
    public void disabledInputTest() {
        String firstName = "Jack";
        String password = "Automation";
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

    /*
    Create an automation test script that Add and remove tags and assert tags presence and display count
    Steps:
    Navigate to website
    https://qaplayground.dev/apps/tags-input-box/
    Print the number of the tags
    Remove all the tags inside the box
    Add 10 tags of any keywords you like
    Verify that Count of number of tags is “0” and print in console
    Now remove all tags and try to input “<script> alert() </script>” as keyword and add it
    Try to get that value of the tag and print in console.
    */
    @Test(priority = 2)
    public void tagsTest() {
        String[] tagsList = {"Automation1", "Automation2", "Automation3", "Automation4", "Automation5", "Automation6", "Automation7", "Automation8", "Automation9", "Automation10"};

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