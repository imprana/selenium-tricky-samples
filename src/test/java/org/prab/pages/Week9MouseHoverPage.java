package org.prab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.NoSuchElementException;

public class Week9MouseHoverPage {
    WebDriver driver;

    private By poster = By.cssSelector("img.poster");
    private By posterContent = By.cssSelector("div.title-content");
    private By currentPrice = By.cssSelector("p.current-price");
    private By oldPrice = By.cssSelector("p.old-price");

    public Week9MouseHoverPage(WebDriver driver) {
        this.driver = driver;
    }

    public void doMouseHoverOnThePoster() {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(poster)).build().perform();
    }

    public boolean isPosterDetailsDisplayed() {
        try {
            return driver.findElement(posterContent).isDisplayed();
        } catch (NoSuchElementException nse) {
            return false;
        }
    }

    public void printPriceDetails() {
        System.out.println("Price details:");
        System.out.println("Current price: " + driver.findElement(currentPrice).getText());
        System.out.println("Old price: " + driver.findElement(oldPrice).getText());
    }
}