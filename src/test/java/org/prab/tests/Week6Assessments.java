package org.prab.tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.prab.fw.TestBase;
import org.prab.pages.Week6ShadowDOMPage;
import org.prab.pages.Week6ShoppingPage;
import org.testng.annotations.Test;

import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Week6Assessments extends TestBase {
    /*
    Navigate to http://uitestingplayground.com/shadowdom
    Create a test that clicks on  and then on  buttons. This sequence of steps generates new guid and copies it to the clipboard.
    Add an assertion step to your test to compare the value from the clipboard with the value of the input field.
    Then execute the test to make sure that the assertion step is not failing.
    */
    @Test(priority = 1)
    public void shadowDOMElementsTest() throws Exception {
        driver.navigate().to("http://uitestingplayground.com/shadowdom");
        Week6ShadowDOMPage shadowDOMPage = new Week6ShadowDOMPage(driver);
        shadowDOMPage.generateGUID();
        Object copiedData = shadowDOMPage.copyGUID();
        System.out.println("Copied GUID: " + copiedData.toString());
        String ipData = shadowDOMPage.getGUIDFromInput();
        System.out.println("GUID from Input field: " + ipData);
        assertEquals(ipData, copiedData, "Copied data is not matched with the input field data");
    }

    /*
    Navigate to https://magento.softwaretestingboard.com/Enter
    create a customer with valid info
    Sign in with the customer credentials you created
    Take any product and add it to the cart
    Verify that the product name, product type, product description (if it exists) & size are the same as you selected
    Proceed to checkout and verify its billing details & its price calculated at billing
    */
    @Test(priority = 2)
    public void shoppingCartTest() throws Exception {
        driver.navigate().to("https://magento.softwaretestingboard.com/");
        Week6ShoppingPage shoppingPage = new Week6ShoppingPage(driver);
        Faker faker = new Faker();

        String fname = faker.name().firstName();
        String lname = faker.name().lastName();
        String email = faker.internet().emailAddress();
        System.out.println("User First Name: " + fname);
        System.out.println("User Last Name: " + lname);
        System.out.println("User Email: " + email);
        String password = "QaCoE@123";
        shoppingPage.createUser(fname, lname, email, password);
        assertTrue(shoppingPage.isMyAccountDisplayed(), "User has not been created");

        driver.findElement(shoppingPage.whatsNew).click();
        WebElement element = driver.findElement(By.xpath(format(shoppingPage.pdtItemByIndex, 1)));
        element.click();

        String name = driver.findElement(shoppingPage.pdtName).getText().trim();
        String price = driver.findElement(shoppingPage.pdtPrice).getText().trim();
        driver.findElement(shoppingPage.addToCart).click();
        Thread.sleep(3000);

        driver.findElement(shoppingPage.showCart).click();
        assertEquals(driver.findElement(shoppingPage.cartPdt).getText(), name);
        assertEquals(driver.findElement(shoppingPage.pdtPrice).getText().trim(), price);
        driver.findElement(shoppingPage.proceedCheckout).click();
        Thread.sleep(3000);
        shoppingPage.closeAddIfExists();
        driver.findElement(shoppingPage.itemInCart).click();

        assertEquals(driver.findElement(shoppingPage.billingFirstName).getAttribute("value"), fname);
        assertEquals(driver.findElement(shoppingPage.billingLastName).getAttribute("value"), lname);
        assertEquals(driver.findElement(shoppingPage.billingPdtName).getText(), name);
        assertEquals(driver.findElement(shoppingPage.billingPrice).getText().trim(), price);
    }
}