package org.prab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Week1MaximumLinksPage {
    WebDriver driver;
    private By linksByTagName= By.tagName("a");

    public Week1MaximumLinksPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getAllLinksAsList() {
        return  driver.findElements(linksByTagName);
    }
}
