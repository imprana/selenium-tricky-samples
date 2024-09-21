package org.prab.tests;

import org.json.JSONObject;
import org.prab.fw.TestBase;
import org.prab.pages.Week4FormsPage;
import org.prab.pages.Week4GrownButtonPage;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class Week4Assessments extends TestBase {
    /*
    Navigate to https://testpages.eviltester.com/styled/challenges/growing-clickable.html
    Click on the growing button and once clicked you should see “Event Triggered” message. Verify that message
    */
    @Test(priority = 1)
    public void growingButtonTest() {
        System.out.println("Week 4 - Problem 1: Click Growing Button");
        driver.navigate().to("https://testpages.eviltester.com/styled/challenges/growing-clickable.html");
        Week4GrownButtonPage grownBtnPage = new Week4GrownButtonPage(driver);
        grownBtnPage.clickGrownBtn();

        String eventMsg = grownBtnPage.getGrownBtnStatus();
        System.out.println("Event Message: " + eventMsg);
        assertEquals(eventMsg, "Event Triggered", "Event message is incorrect");
    }

    /*
    Navigate to https://fs2.formsite.com/meherpavan/form2/index.html?1537702596407
    For the fields Firstname and Lastname, use getapi https://randomuser.me/api/
    Fill rest of the form and
    Don’t click Submit
    */
    @Test(priority = 2)
    public void formDetailsWithAPIResponse() throws IOException, InterruptedException {
        System.out.println("Week 4 - Problem 2-1: Fill the Form thru API response (HTTP)");
        driver.navigate().to("https://fs2.formsite.com/meherpavan/form2/index.html?1537702596407");
        Week4FormsPage formsPage = new Week4FormsPage(driver);
        String data = formsPage.retrieveAPIResponse("https://randomuser.me/api");
        JSONObject object = formsPage.jsonNameHandler(data);
        String fname = formsPage.getFirstName(object);
        String lname = formsPage.getLastName(object);
        System.out.println("First Name from API: " + fname);
        System.out.println("Last Name from API: " + lname);

        String file = System.getProperty("user.dir") + File.separator + "data" + File.separator + "uploadfile.JPG";
        formsPage.enterFormDetails(fname, lname, "9876543210", "India", "Chennai",
                "dummy@dummy.com", "Male", "Tuesday", "Evening", false, file);
    }

    @Test(priority = 3)
    public void formDetailsWithAPIResponse2() {
        System.out.println("Week 4 - Problem 2-2: Fill the Form thru API response (Selenium)");
        driver.navigate().to("https://randomuser.me/api");
        Week4FormsPage formsPage = new Week4FormsPage(driver);
        String data = formsPage.getDataFromPage();
        JSONObject object = formsPage.jsonNameHandler(data);
        String fname = formsPage.getFirstName(object);
        String lname = formsPage.getLastName(object);
        System.out.println("First Name from Page: " + fname);
        System.out.println("Last Name from Page: " + lname);

        driver.navigate().to("https://fs2.formsite.com/meherpavan/form2/index.html?1537702596407");
        String file = System.getProperty("user.dir") + File.separator + "data" + File.separator + "uploadfile.JPG";
        formsPage.enterFormDetails(fname, lname, "9876543210", "India", "Chennai",
                "dummy@dummy.com", "Female", "Saturday", "Morning", false, file);
    }
}