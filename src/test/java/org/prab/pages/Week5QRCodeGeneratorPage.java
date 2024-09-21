package org.prab.pages;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;

public class Week5QRCodeGeneratorPage {
    WebDriver driver;
    private By urlOrText = By.xpath("//input[@placeholder='Enter text or URL']");
    private By generateQRCodeBtn = By.xpath("//button[text()='Generate QR Code']");
    private By qrSrc = By.xpath("//img[@alt='qr-code']");

    public Week5QRCodeGeneratorPage(WebDriver driver) {
        this.driver = driver;
    }

    public void generateQRCode(String text) {
        WebElement element = driver.findElement((urlOrText));
        element.clear();
        element.sendKeys(text);
        driver.findElement(generateQRCodeBtn).click();
    }

    public String extractURLFromQR() {
        WebElement element = driver.findElement(qrSrc);
        return element.getAttribute("src").trim();
    }

    public String extractDataFromQRCode(String qrData) throws Exception {
        URL url = new URL(qrData);
        BufferedImage bufferedimage = ImageIO.read(url);

        LuminanceSource luminanceSource = new BufferedImageLuminanceSource(bufferedimage);
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(luminanceSource));

        Result result = new MultiFormatReader().decode(binaryBitmap);
        return result.getText();
    }
}
