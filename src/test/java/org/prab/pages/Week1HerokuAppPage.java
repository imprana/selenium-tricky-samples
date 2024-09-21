package org.prab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Week1HerokuAppPage {
    WebDriver driver;
    private final By authSuccessTitle = By.xpath("//div[@class='example']/h3");
    private final By authSuccessContent = By.xpath("//div[@class='example']/p");

    public Week1HerokuAppPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getAuthSuccessTitle() {
        return driver.findElement(authSuccessTitle).getText().trim();
    }

    public String getAuthSuccessContent() {
        return driver.findElement(authSuccessContent).getText().trim();
    }
}
