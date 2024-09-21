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

public class Week9DownloadPage {
    WebDriver driver;

    private By downloadBtn = By.xpath("//a[text()='Download']");

    public Week9DownloadPage(WebDriver driver) {
        this.driver = driver;
    }

    public void doDownload() {
        driver.findElement(downloadBtn).click();
    }
}