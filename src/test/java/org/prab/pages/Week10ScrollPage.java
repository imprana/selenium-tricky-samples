package org.prab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Week10ScrollPage {
    WebDriver driver;

    private final By infoMsg = By.id("info");
    private final By youFoundMe = By.id("fugitive");

    public Week10ScrollPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getInfoMessage() {
        return driver.findElement(infoMsg).getText().trim();
    }

    public void clickYouFoundMe() {
        WebElement element = driver.findElement(youFoundMe);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView();", element);
        executor.executeScript("arguments[0].click();", element);
    }
}