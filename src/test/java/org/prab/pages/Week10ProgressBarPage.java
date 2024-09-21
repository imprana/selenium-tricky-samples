package org.prab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Week10ProgressBarPage {
    WebDriver driver;

    private final By startButton = By.id("startButton");
    private final By stopButton = By.id("stopButton");
    private final By progressBar = By.id("progressBar");

    public Week10ProgressBarPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickStartButton() {
        driver.findElement(startButton).click();
    }

    public void clickStopButton() {
        driver.findElement(stopButton).click();
    }

    public void waitForProgressBar(int percentage) {
        while (true) {
            int currentPercentage = Integer.parseInt(driver.findElement(progressBar).getText().trim().replace("%", ""));
            if (currentPercentage >= percentage) {
                clickStopButton();
                break;
            }
        }
        System.out.println("Progress bar percentage: " + driver.findElement(progressBar).getText().trim());
    }
}