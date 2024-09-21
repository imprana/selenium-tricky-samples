package org.prab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Week3StarRatingsPage {
    WebDriver driver;
    private By starRating = By.className("star-rating");
    private By ratingInput = By.id("txt_rating");
    private By checkRatingBtn = By.id("check_rating");
    private By validateRatingText = By.id("validate_rating");

    public Week3StarRatingsPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getStarRating() {
        WebElement element = driver.findElement(starRating);
        String script = "return window.getComputedStyle(arguments[0], arguments[1]).getPropertyValue(arguments[2]);";
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        String rating = (String) executor.executeScript(script, element, ":after", "content");
        return rating.replaceAll("\"", "");
    }

    public void enterStarRating(String rating) {
        WebElement element = driver.findElement(ratingInput);
        element.clear();
        element.sendKeys(rating);
    }

    public void validateStarRating(String rating) {
        enterStarRating(rating);
        driver.findElement(checkRatingBtn).click();
    }

    public String getSuccessMessage() {
        return driver.findElement(validateRatingText).getText().trim();
    }
}
