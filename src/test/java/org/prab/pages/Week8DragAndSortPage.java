package org.prab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;

import static java.lang.String.format;
import static org.testng.Assert.assertEquals;

public class Week8DragAndSortPage {
    WebDriver driver;

    private By checkOrderBtn = By.id("check");
    private String position = "//li[@data-index='%s']";//span[text()='%s']";
    private String person = "//p[text()='%s']";
    private String personByIndex = "(//p[@class='person-name'])[%s]";

    public Week8DragAndSortPage(WebDriver driver) {
        this.driver = driver;
    }

    public void doSorting(String[] list) throws Exception {
        zoomOutPage();
        Actions actions = new Actions(driver);
        for (int i = 0; i < list.length; i++) {
            WebElement personToDrag = waitForElement(By.xpath(format(person, list[i])));
            WebElement targetPosition = waitForElement(By.xpath(format(position, i)));
            actions.dragAndDrop(personToDrag, targetPosition).perform();
        }
    }

    public void clickCheckOrderBtn() {
        driver.findElement(checkOrderBtn).click();
    }

    public void doAssertionsForSortedList(String[] list) {
        for (int i = 1; i <= list.length; i++) {
            WebElement personName = driver.findElement(By.xpath(format(personByIndex, i)));
            System.out.println("Richest person at position: " + i + " - " + personName.getText());
            assertEquals(personName.getText().trim(), list[i - 1], "Incorrect richest person at position: " + i);
            assertEquals(personName.getCssValue("color"), "rgba(58, 227, 116, 1)", "Incorrect richest person at position: " + i);
        }
    }

    private WebElement waitForElement(By selector) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
    }

    private void zoomOutPage() throws InterruptedException, AWTException {
        Robot robot = new Robot();

        System.out.println("About to zoom out");
        for (int i = 0; i < 5; i++) {
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_SUBTRACT);
            robot.keyRelease(KeyEvent.VK_SUBTRACT);
            robot.keyRelease(KeyEvent.VK_CONTROL);
        }
        Thread.sleep(5000);
    }
}