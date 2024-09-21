package org.prab.pages;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

public class Week5VerifyAccountPage {
    WebDriver driver;

    private By otpInput = By.xpath("//input[@type='number']");
    private By success = By.xpath("//small[contains(@class, 'success')]");
    private By otpCode = By.xpath("//small[@class='info']");

    public Week5VerifyAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public int[] extractOTP() {
        String input = driver.findElement(otpCode).getText().trim();
        String digits = StringUtils.getDigits(input);
        int[] otp = Arrays.stream(digits.split("")).mapToInt(Integer::parseInt).toArray();
        return otp;
    }
    public void verificationCodeByKeyEvents(int[] otp) {
        List<WebElement> elements = driver.findElements(otpInput);

        int i = 0;
        for (WebElement element : elements) {
            element.clear();
            typeOTP(element, otp[i]);
            i++;
        }
    }

    private void typeOTP(WebElement element, int num) {
        switch (num) {
            case 0:
                element.sendKeys(Keys.NUMPAD0);
                break;
            case 1:
                element.sendKeys(Keys.NUMPAD1);
                break;
            case 2:
                element.sendKeys(Keys.NUMPAD2);
                break;
            case 3:
                element.sendKeys(Keys.NUMPAD3);
                break;
            case 4:
                element.sendKeys(Keys.NUMPAD4);
                break;
            case 5:
                element.sendKeys(Keys.NUMPAD5);
                break;
            case 6:
                element.sendKeys(Keys.NUMPAD6);
                break;
            case 7:
                element.sendKeys(Keys.NUMPAD7);
                break;
            case 8:
                element.sendKeys(Keys.NUMPAD8);
                break;
            case 9:
                element.sendKeys(Keys.NUMPAD9);
                break;
        }
    }

    public String getSuccessMessage() {
        return driver.findElement(success).getText().trim();
    }
}
