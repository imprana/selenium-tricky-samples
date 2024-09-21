package org.prab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Week4GrownButtonPage {
    WebDriver driver;
    private By grownBtn = By.id("growbutton");
    private By grownBtnStatus = By.id("growbuttonstatus");

    public Week4GrownButtonPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickGrownBtn() {
        WebElement element = driver.findElement(grownBtn);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.attributeContains(element, "class", "grown"));
        element.click();
    }

    public String getGrownBtnStatus() {
        return driver.findElement(grownBtnStatus).getText().trim();
    }
}
