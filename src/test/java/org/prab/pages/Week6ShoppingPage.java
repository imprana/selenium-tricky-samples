package org.prab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Week6ShoppingPage {
    WebDriver driver;

    private By createAccount = By.linkText("Create an Account");
    private By firstname = By.id("firstname");
    private By lastname = By.id("lastname");
    private By email = By.id("email_address");
    private By password = By.id("password");
    private By cnfPassword = By.id("password-confirmation");
    private By createAccountBtn = By.xpath("//button[@title='Create an Account']");
    public By whatsNew = By.id("ui-id-3");

    public By adHide=By.xpath("//div[@class=\"ea-stickybox-hide\"]");
    public String pdtItemByIndex = "(//a[@class='product-item-link'])[%s]";
    public By pdtName = By.xpath("//span[@itemprop='name']");
    public By pdtPrice = By.xpath("//span[@class='price']");
    public By addToCart = By.xpath("//button[@title='Add to Cart']");
    public By cartPdt = By.xpath("//strong[@class='product-item-name']/a");

    public By showCart = By.xpath("//a[contains(@class, 'showcart')]");
    public By proceedCheckout = By.id("top-cart-btn-checkout");

    public By itemInCart = By.xpath("//span[text()='Item in Cart']");
    public By billingFirstName = By.name("firstname");
    public By billingLastName = By.name("lastname");
    public By billingPdtName = By.xpath("//strong[@class='product-item-name']");
    public By billingPrice = By.xpath("//span[@class='cart-price']/span");

    private By myAccountHeader = By.xpath("//h1/span[text()='My Account']");

    public Week6ShoppingPage(WebDriver driver) {
        this.driver = driver;
    }

    public void createUser(String fname, String lname, String userEmail, String userPass) {
        driver.findElement(createAccount).click();
        driver.findElement(firstname).clear();
        driver.findElement(firstname).sendKeys(fname);
        driver.findElement(lastname).clear();
        driver.findElement(lastname).sendKeys(lname);
        driver.findElement(email).clear();
        driver.findElement(email).sendKeys(userEmail);
        driver.findElement(password).clear();
        driver.findElement(password).sendKeys(userPass);
        driver.findElement(cnfPassword).clear();
        driver.findElement(cnfPassword).sendKeys(userPass);
        driver.findElement(createAccountBtn).click();
    }

    public boolean isMyAccountDisplayed() {
        return driver.findElement(myAccountHeader).isDisplayed();
    }
}