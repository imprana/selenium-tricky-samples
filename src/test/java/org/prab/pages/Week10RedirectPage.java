package org.prab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Week10RedirectPage {
    WebDriver driver;

    private final By startRedirectionChainBtn = By.id("redirect");
    private final By infoMsg = By.id("info");
    private final By goBackBtn = By.xpath("//a[text()='Go Back']");

    public Week10RedirectPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickStartRedirectionChainBtn() {
        driver.findElement(startRedirectionChainBtn).click();
    }

    public void clickGoBackBtn() {
        driver.findElement(goBackBtn).click();
    }

    public void validatePages(String... pages) {
        String message = "Welcome to %s Page";
        String title = "%s Page";
        String URL = "https://qaplayground.dev/apps/redirect/%s";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        System.out.println("Initial page title: " + driver.getTitle());
        System.out.println("Initial page URL: " + driver.getCurrentUrl());
        clickStartRedirectionChainBtn();

        for (String page : pages) {
            wait.until(ExpectedConditions.urlContains(page.toLowerCase()));
            System.out.println(page + " page message: " + driver.findElement(infoMsg).getText().trim());
            assertEquals(driver.findElement(infoMsg).getText().trim(), format(message, page), page + " page message is incorrect");
            System.out.println(page + " page title: " + driver.getTitle());
            assertEquals(driver.getTitle(), format(title, page), page + " page title is incorrect");
            System.out.println(page + " page URL: " + driver.getCurrentUrl());
            assertEquals(driver.getCurrentUrl(), format(URL, page.toLowerCase()), page + " page URL is incorrect");
        }

        // Last Page
        wait.until(ExpectedConditions.urlContains("last"));
        System.out.println("Last page message: " + driver.findElement(infoMsg).getText().trim());
        assertEquals(driver.findElement(infoMsg).getText().trim(), format(message, "the Last"), "Last page message is incorrect");
        System.out.println("Last page title: " + driver.getTitle());
        assertEquals(driver.getTitle(), format(title, "Last"), "Last page title is incorrect");
        assertEquals(driver.getCurrentUrl(), format(URL, "last"), "Last page URL is incorrect");
        System.out.println("Last page URL: " + driver.getCurrentUrl());

        WebElement element = driver.findElement(goBackBtn);
        assertTrue(element.isDisplayed(), "Go Back button has not been displayed.");
        clickGoBackBtn();

        // Initial Page
        WebElement element1 = driver.findElement(startRedirectionChainBtn);
        wait.until(ExpectedConditions.visibilityOf(element1));
        assertTrue(element1.isDisplayed(), "Start Redirection chain button has not been displayed.");
        System.out.println("First page title: " + driver.getTitle());
        assertEquals(driver.getTitle(), "Test Redirection Chain", "First page title is incorrect");
        System.out.println("First page URL: " + driver.getCurrentUrl());
        assertEquals(driver.getCurrentUrl(), "https://qaplayground.dev/apps/redirect/", "First page URL is incorrect");
    }
}
