package org.prab.fw;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;

public class TestBase {
    protected static WebDriver driver;
    protected String downloadPath = System.getProperty("user.dir") + File.separator + "downloads";

    @BeforeClass(alwaysRun = true)
    public void startUp() {
        // Supports only Chrome as of now
        try {
            HashMap<String, Object> prefs = new HashMap<>();
            prefs.put("download.default_directory", downloadPath);
            prefs.put("download.prompt_for_download", false);
            prefs.put("plugins.plugins_disabled", new String[]{"Chrome PDF Viewer"});
            prefs.put("plugins.always_open_pdf_externally", true);
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", prefs);

            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}