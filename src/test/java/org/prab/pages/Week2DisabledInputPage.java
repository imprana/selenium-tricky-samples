package org.prab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Week2DisabledInputPage {
    WebDriver driver;
    private By firstName = By.id("fname");
    private By password = By.id("pass");
    private By newPassword = By.id("passnew");
    private By showMeBtn = By.xpath("//input[@value='Show me']");

    public Week2DisabledInputPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterFirstName(String name) {
        WebElement element = driver.findElement(firstName);
        element.clear();
        element.sendKeys(name);
    }

    public String getFirstName() {
        return  driver.findElement(firstName).getAttribute("value").trim();
    }
    public void enterPassword(String pass) {
        WebElement element = driver.findElement(password);
        if (!element.isEnabled()) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].removeAttribute('disabled');", element);
        }
        element.clear();
        element.sendKeys(pass);
    }

    public String getPassword() {
        return  driver.findElement(password).getAttribute("value").trim();
    }

    public void enterNewPassword(String pass) {
        clickShowMeBtn();
        WebElement element = driver.findElement(newPassword);
        // Element will be enabled after 15 seconds
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(16));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.clear();
        element.sendKeys(pass);
    }

    public String getNewPassword() {
        return  driver.findElement(newPassword).getAttribute("value").trim();
    }

    public void clickShowMeBtn() {
        driver.findElement(showMeBtn).click();
    }
}
