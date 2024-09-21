package org.prab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static java.lang.String.format;

public class Week7EcommercePage {
    WebDriver driver;

    private final By megaMenu = By.xpath("//span[contains(text(), 'Mega Menu')]");
    private final String megaMenuOption = "//a[@title='%s']";

    private final By productThumb = By.className("product-thumb");
    private final By productsToShow = By.id("input-limit-212433");

    private final By productName = By.cssSelector(".caption h4");
    private final By productPrice = By.cssSelector(".price");

    private final By sortDD = By.id("input-sort-212434");


    public Week7EcommercePage(WebDriver driver) {
        this.driver = driver;
    }

    public void hoverMegaMenu() {
        WebElement element = driver.findElement(megaMenu);
        Actions actions = new Actions(driver);
        Action mouseOverHome = actions.moveToElement(element).build();
        mouseOverHome.perform();
    }

    public void clickMegaMenuOption(String option) {
        hoverMegaMenu();
        driver.findElement(By.xpath(format(megaMenuOption, option))).click();
    }

    public void sortProducts(String option) {
        Select select = new Select(driver.findElement(sortDD));
        select.selectByVisibleText(option);
    }

    public void setProductsToShow(String option) {
        Select select = new Select(driver.findElement(productsToShow));
        select.selectByVisibleText(option);
    }

    public int getProductsCount() {
        return driver.findElements(productThumb).size();
    }

    public void printProductDetailsByLimit(int limit) {
        List<WebElement> elements = driver.findElements(productThumb);
        for (int i = 0; i < limit; i++) {
            String name = elements.get(i).findElement(productName).getText().trim();
            String price = elements.get(i).findElement(productPrice).getText().trim();
            System.out.println("Product: " + (i + 1) + " - Name: " + name + ", Price: " + price);
        }
    }
}