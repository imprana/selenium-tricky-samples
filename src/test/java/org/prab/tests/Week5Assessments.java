package org.prab.tests;

import com.github.javafaker.Faker;
import org.prab.fw.TestBase;
import org.testng.annotations.Test;
import org.prab.pages.Week5QRCodeGeneratorPage;
import org.prab.pages.Week5VerifyAccountPage;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;

public class Week5Assessments extends TestBase {
    @Test(priority = 1)
    public void verifyAccountByKeyEvents() throws Exception {
        driver.navigate().to("https://qaplayground.dev/apps/verify-account/");
        Week5VerifyAccountPage verifyAccountPage = new Week5VerifyAccountPage(driver);

        // Extract OTP
        int[] otp = verifyAccountPage.extractOTP();
        System.out.println("Extracted OTP: " + Arrays.toString(otp));
        verifyAccountPage.verificationCodeByKeyEvents(otp);
        assertEquals(verifyAccountPage.getSuccessMessage(), "Success", "Success message is not displayed");
    }

    @Test(priority = 2)
    public void qrCodeGeneratorTest() throws Exception {
        driver.navigate().to("https://qaplayground.dev/apps/qr-code-generator/");
        Week5QRCodeGeneratorPage qrCodeGeneratorPage = new Week5QRCodeGeneratorPage(driver);
        String data = "I am Test automation Engineer";

        // Generate QR Code
        qrCodeGeneratorPage.generateQRCode(data);
        // Extract URL from QR
        String url = qrCodeGeneratorPage.extractURLFromQR();
        System.out.println("Extracted URL from QR: " + url);

        // Extract data from QR
        String qrData = qrCodeGeneratorPage.extractDataFromQRCode(url);
        System.out.println("Given data: " + data);
        System.out.println("Extracted Data from QR: " + qrData);
        assertEquals(qrData, data, "Given data is not match with QR data");
    }
}