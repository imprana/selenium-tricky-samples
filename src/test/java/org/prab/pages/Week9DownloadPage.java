package org.prab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Week9DownloadPage {
    WebDriver driver;

    private final By downloadBtn = By.xpath("//a[text()='Download']");

    public Week9DownloadPage(WebDriver driver) {
        this.driver = driver;
    }

    public void doDownload() {
        driver.findElement(downloadBtn).click();
    }
}