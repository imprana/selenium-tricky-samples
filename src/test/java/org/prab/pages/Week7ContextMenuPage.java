package org.prab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class Week7ContextMenuPage {
    WebDriver driver;

    private By bodyContent = By.xpath("//body[not(self::nav)]");
    private By shareMenu = By.xpath("//span[text()='Share']");
    private By shareMenuOptions = By.xpath("//ul[@class='share-menu']//li");
    private String shareMedia = "//span[text()='%s']";
    private By message = By.id("msg");

    public Week7ContextMenuPage(WebDriver driver) {
        this.driver = driver;
    }

    public void doContextClick() {
        WebElement element = driver.findElement(bodyContent);
        Actions actions = new Actions(driver);
        actions.contextClick(element).build().perform();
    }

    public void doShareAndTest() {
        List<WebElement> elements = driver.findElements(shareMenuOptions);
        for (WebElement element : elements) {
            doContextClick();
            Actions actions = new Actions(driver);
            Action mouseOverHome = actions.moveToElement(driver.findElement(shareMenu)).build();
            mouseOverHome.perform();
            element.click();
            String message = getMessage();
            System.out.println("Message: " + message);
            assertEquals(message, "Menu item " + element.getText().trim() + " clicked", "Message is incorrect");
        }
    }

    public String getMessage() {
        return driver.findElement(message).getText().trim();
    }
}