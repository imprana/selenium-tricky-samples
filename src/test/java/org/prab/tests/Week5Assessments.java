package org.prab.tests;

import org.prab.fw.TestBase;
import org.prab.pages.Week5QRCodeGeneratorPage;
import org.prab.pages.Week5VerifyAccountPage;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;

public class Week5Assessments extends TestBase {
    /*
    Navigate to https://qaplayground.dev/apps/verify-account/
    Enter the verification code “999999”
    Verify “Success” message
    */
    @Test(priority = 1)
    public void verifyAccountByKeyEvents() {
        driver.navigate().to("https://qaplayground.dev/apps/verify-account/");
        Week5VerifyAccountPage verifyAccountPage = new Week5VerifyAccountPage(driver);

        // Extract OTP
        int[] otp = verifyAccountPage.extractOTP();
        System.out.println("Extracted OTP: " + Arrays.toString(otp));
        verifyAccountPage.verificationCodeByKeyEvents(otp);
        assertEquals(verifyAccountPage.getSuccessMessage(), "Success", "Success message is not displayed");
    }

    /*
    Navigate to https://qaplayground.dev/apps/qr-code-generator/
    Enter “I am Test automation Engineer” in the text field
    Click on “Generate QR code” button
    Fetch the text content of the generated QR Code
    Verify the text extracted is equal to the input text
    */
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