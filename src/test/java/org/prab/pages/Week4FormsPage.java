package org.prab.pages;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.lang.String.format;
import static org.testng.Assert.assertEquals;

public class Week4FormsPage {
    WebDriver driver;

    private By firstName = By.id("RESULT_TextField-1");
    private By lastName = By.id("RESULT_TextField-2");
    private By phoneIp = By.id("RESULT_TextField-3");

    private By countryIp = By.id("RESULT_TextField-4");
    private By cityIp = By.id("RESULT_TextField-5");
    private By emailIp = By.id("RESULT_TextField-6");

    private String genderIp = "//label[text()='%s']";
    private String genderRadio = "//label[text()='%s']/preceding-sibling::input";
    private String consistencyIp = "//label[text()='%s']";
    private String consistencyChkBox = "//label[text()='%s']/preceding-sibling::input";
    private By bestTimeIp = By.id("RESULT_RadioButton-9");
    private By uploadIp = By.id("RESULT_FileUpload-10");

    private By response = By.tagName("pre");

    public Week4FormsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterFormDetails(String fname, String lname, String phone, String country,
                                 String city, String email, String gender, String consistency,
                                 String time, boolean doUpload, String upload) {
        // First name
        driver.findElement(firstName).clear();
        driver.findElement(firstName).sendKeys(fname);

        // Last name
        driver.findElement(lastName).clear();
        driver.findElement(lastName).sendKeys(lname);

        // Phone
        driver.findElement(phoneIp).clear();
        driver.findElement(phoneIp).sendKeys(phone);

        // Country
        driver.findElement(countryIp).clear();
        driver.findElement(countryIp).sendKeys(country);

        // City
        driver.findElement(cityIp).clear();
        driver.findElement(cityIp).sendKeys(city);

        // Email
        driver.findElement(emailIp).clear();
        driver.findElement(emailIp).sendKeys(email);

        // Gender
        driver.findElement(By.xpath(format(genderIp, gender))).click();

        // Consistency
        driver.findElement(By.xpath(format(consistencyIp, consistency))).click();

        // Best Time
        WebElement element = driver.findElement(bestTimeIp);
        Select select = new Select(element);
        select.selectByVisibleText(time);

        validateFormDetails(fname, lname, phone, country, city, email, gender, consistency, time);
        // Upload
        if (doUpload)
            driver.findElement(uploadIp).sendKeys(upload);
    }

    public void validateFormDetails(String fname, String lname, String phone, String country,
                                    String city, String email, String gender, String consistency,
                                    String time) {
        System.out.println("Entered form details");
        System.out.println("--------------------");
        System.out.println("First Name: " + driver.findElement(firstName).getAttribute("value"));
        assertEquals(driver.findElement(firstName).getAttribute("value"), fname, "First name is incorrect");

        System.out.println("Last Name: " + driver.findElement(lastName).getAttribute("value"));
        assertEquals(driver.findElement(lastName).getAttribute("value"), lname, "Last name is incorrect");

        System.out.println("Phone: " + driver.findElement(phoneIp).getAttribute("value"));
        assertEquals(driver.findElement(phoneIp).getAttribute("value"), phone, "Phone is incorrect");

        System.out.println("Country: " + driver.findElement(countryIp).getAttribute("value"));
        assertEquals(driver.findElement(countryIp).getAttribute("value"), country, "Country is incorrect");

        System.out.println("City: " + driver.findElement(cityIp).getAttribute("value"));
        assertEquals(driver.findElement(cityIp).getAttribute("value"), city, "City is incorrect");

        System.out.println("Email: " + driver.findElement(emailIp).getAttribute("value"));
        assertEquals(driver.findElement(emailIp).getAttribute("value"), email, "Email is incorrect");

        System.out.println("Gender Status: " + driver.findElement(By.xpath(format(genderRadio, gender))).isSelected());
        assertEquals(driver.findElement(By.xpath(format(genderRadio, gender))).isSelected(), true, "Gender is incorrect");

        System.out.println("Consistency Status: " + driver.findElement(By.xpath(format(consistencyChkBox, consistency))).isSelected());
        assertEquals(driver.findElement(By.xpath(format(consistencyChkBox, consistency))).isSelected(), true, "Consistency is incorrect");

        WebElement element = driver.findElement(bestTimeIp);
        Select select = new Select(element);
        System.out.println("Best time: " + select.getFirstSelectedOption().getText());
        assertEquals(select.getFirstSelectedOption().getText(), time, "Best time is incorrect");
    }

    public String getDataFromPage() {
        return driver.findElement(response).getText().trim();
    }

    public String retrieveAPIResponse(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public JSONObject jsonNameHandler(String data) {
        JSONObject object = new JSONObject(data);
        JSONArray array = object.getJSONArray("results");
        JSONObject name = array.getJSONObject(0).getJSONObject("name");
        return name;
    }

    public String getFirstName(JSONObject data) {
        return data.getString("first");
    }

    public String getLastName(JSONObject data) {
        return data.getString("last");
    }
}
