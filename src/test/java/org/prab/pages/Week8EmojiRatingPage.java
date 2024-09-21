package org.prab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static java.lang.String.format;
import static org.testng.Assert.assertEquals;

public class Week8EmojiRatingPage {
    WebDriver driver;

    private By stars = By.xpath("//div[@class='stars']/label");
    private String starsLabel = "//label[@for='star-%s']";
    private By ratingText = By.cssSelector("span.text");
    private By ratingNumber = By.cssSelector("span.numb");
    private String emoji = "//img[@src='emojis/emoji-%s.png']";

    public Week8EmojiRatingPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getStars() {
        return driver.findElements(stars);
    }

    public void clickStars(int index) {
        WebElement element = driver.findElement(By.xpath((format(starsLabel, index))));
        element.click();
    }

    public String getRatingComment() {
        WebElement element = driver.findElement(ratingText);
        String script = "return window.getComputedStyle(arguments[0], arguments[1]).getPropertyValue(arguments[2]);";
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        String rating = (String) executor.executeScript(script, element, ":before", "content");
        return rating.replaceAll("\"", "");
    }

    public String getRatingNumber() {
        WebElement element = driver.findElement(ratingNumber);
        String script = "return window.getComputedStyle(arguments[0], arguments[1]).getPropertyValue(arguments[2]);";
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        String rating = (String) executor.executeScript(script, element, ":before", "content");
        return rating.replaceAll("\"", "");
    }

    public void doRatingAssertions(int star) {
        switch (star) {
            case 1:
                System.out.println("Validating Star rating: " + star);
                WebElement element1 = waitForEmoji(By.xpath(format(emoji, star)));
                assertEquals(element1.isDisplayed(), true, "Emoji is not displayed correctly for Rating Star: " + star);
                assertEquals(getRatingComment(), "I just hate it", "Comment is incorrect for Rating Star: " + star);
                assertEquals(getRatingNumber(), "1 out of 5", "Number is incorrect for Rating Star: " + star);
                break;
            case 2:
                System.out.println("Validating Star rating: " + star);
                WebElement element2 = waitForEmoji(By.xpath(format(emoji, star)));
                assertEquals(element2.isDisplayed(), true, "Emoji is not displayed correctly for Rating Star: " + star);
                assertEquals(getRatingComment(), "I don't like it", "Comment is incorrect for Rating Star: " + star);
                assertEquals(getRatingNumber(), "2 out of 5", "Number is incorrect for Rating Star: " + star);
                break;
            case 3:
                System.out.println("Validating Star rating: " + star);
                WebElement element3 = waitForEmoji(By.xpath(format(emoji, star)));
                assertEquals(element3.isDisplayed(), true, "Emoji is not displayed correctly for Rating Star: " + star);
                assertEquals(getRatingComment(), "This is awesome", "Comment is incorrect for Rating Star: " + star);
                assertEquals(getRatingNumber(), "3 out of 5", "Number is incorrect for Rating Star: " + star);
                break;
            case 4:
                System.out.println("Validating Star rating: " + star);
                WebElement element4 = waitForEmoji(By.xpath(format(emoji, star)));
                assertEquals(element4.isDisplayed(), true, "Emoji is not displayed correctly for Rating Star: " + star);
                assertEquals(getRatingComment(), "I just like it", "Comment is incorrect for Rating Star: " + star);
                assertEquals(getRatingNumber(), "4 out of 5", "Number is incorrect for Rating Star: " + star);
                break;
            case 5:
                System.out.println("Validating Star rating: " + star);
                WebElement element5 = waitForEmoji(By.xpath(format(emoji, star)));
                assertEquals(element5.isDisplayed(), true, "Emoji is not displayed correctly for Rating Star: " + star);
                assertEquals(getRatingComment(), "I just love it", "Comment is incorrect for Rating Star: " + star);
                assertEquals(getRatingNumber(), "5 out of 5", "Number is incorrect for Rating Star: " + star);
                break;
            default:
                System.out.println("No star matched");
                throw new IllegalStateException("Unexpected value: " + star);
        }
    }

    private WebElement waitForEmoji(By selector) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
    }
}