package org.prab.tests;

import com.github.javafaker.Faker;
import org.prab.fw.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.prab.pages.Week6ShadowDOMPage;
import org.prab.pages.Week6ShoppingPage;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.Duration;

import static java.lang.String.format;
import static org.testng.Assert.assertEquals;

public class Week6Assessments extends TestBase {
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

    @Test(priority = 2)
    public void shoppingCartTest() throws Exception {
        driver.navigate().to("https://magento.softwaretestingboard.com/");
        Week6ShoppingPage shoppingPage = new Week6ShoppingPage(driver);
        Faker faker = new Faker();

        String fname=faker.name().firstName();
        String lname = faker.name().lastName();
        String email = faker.internet().emailAddress();
        System.out.println("User First Name: " + fname);
        System.out.println("User Last Name: " + lname);
        System.out.println("User Email: " + email);
        String password = "QaCoE@123";
        shoppingPage.createUser(fname,lname, email, password);
        assertEquals(shoppingPage.isMyAccountDisplayed(), true, "User has not been created");

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
        driver.findElement(shoppingPage.adHide).click();
        driver.findElement(shoppingPage.itemInCart).click();

        assertEquals(driver.findElement(shoppingPage.billingFirstName).getAttribute("value"), fname);
        assertEquals(driver.findElement(shoppingPage.billingLastName).getAttribute("value"), lname);
        assertEquals(driver.findElement(shoppingPage.billingPdtName).getText(), name);
        assertEquals(driver.findElement(shoppingPage.billingPrice).getText().trim(), price);
    }
}