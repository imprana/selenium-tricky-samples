package org.prab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Week3DownloadPage {
    WebDriver driver;
    private final By downloadLink = By.xpath("//strong[text()='Download the printable PDF of Selenium cheat sheet']");

    public Week3DownloadPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickDownloadLink() {
        driver.findElement(downloadLink).click();
    }
}
