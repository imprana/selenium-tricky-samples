package org.prab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

public class Week2TagsPage {
    WebDriver driver;
    private final By tagsInput = By.xpath("//div[@class='content']//input");
    private final By selectedTagsList = By.xpath("//div[@class='content']/ul/li");
    public By removeAllBtn = By.xpath("//button[text()='Remove All']");

    public Week2TagsPage(WebDriver driver) {
        this.driver = driver;
    }

    public ArrayList<String> getSelectedTagsAsValue() {
        List<WebElement> elements = driver.findElements(selectedTagsList);
        ArrayList<String> list = new ArrayList<>();
        elements.forEach(element -> list.add(element.getText().trim()));
        return list;
    }

    public List<WebElement> getSelectedTagsAsElements() {
        return driver.findElements(selectedTagsList);
    }

    public void enterTags(String... tags) {
        WebElement element = driver.findElement(tagsInput);
        for (String tag : tags) {
            element.sendKeys(tag);
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.ENTER).build().perform();
        }
    }

    public void clickRemoveAllBtn() {
        driver.findElement(removeAllBtn).click();
    }
}
