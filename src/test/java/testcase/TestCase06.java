package testcase;

import POM.CartPage;
import POM.LoginPage;
import POM.PCheckOut;
import driver.driverFactory;
import org.openqa.selenium.*;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TestCase06 {
    @Test
    public static void testCase06() {
        // Set up the WebDriver
        WebDriver driver = driverFactory.getChromeDriver();
        driver.get("http://live.techpanda.org/");

        // Step 2: Click on My Account link
        WebElement Account = driver.findElement(By.xpath("//span[normalize-space()='Account']"));
        Account.click();
        WebElement myAccountLink = driver.findElement(By.cssSelector("div[id='header-account'] a[title='My Account']"));
        myAccountLink.click();

        // Step 3: Login using previously created credentials
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillLoginForm("hihi1221@gmail.com", "123456");
        loginPage.clickLogin();

        // switching to new window
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
        // Step 4: Click on MY WISHLIST link
        WebElement myWishlistLink = driver.findElement(By.xpath("//div[@class='block-content']//a[normalize-space()='My Wishlist']"));
        myWishlistLink.click();

//        // switching to new window
//        for (String handle : driver.getWindowHandles()) {
//            driver.switchTo().window(handle);
//        }

        // Step 5: Click ADD TO CART link
        driver.findElement(By.xpath("//span[contains(text(),'Add to Cart')]")).click();

        // switching to new window
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }

        //Get the total amount before estimating and updating
        CartPage cartPage = new CartPage(driver);
        String totalBeforeEstimateAndUpdate = cartPage.getTotalAmount();

        // Step 6: Enter general shipping country, state/province, and zip for the shipping cost estimate
        cartPage.estimateInfo("United States", "Ohio", "43001");

        // Step 7: Estimate and update shipping costs
        cartPage.estimateShippingCost();

        // Step 8: Verify Shipping cost generated
        boolean isShippingCostGenerated = cartPage.isShippingCostGenerated();
        assert isShippingCostGenerated : "Shipping cost is not generated.";
        // Step 9: Select Shipping Cost, Update Total
        cartPage.selectShippingCost();
        cartPage.updateTotal();

        // Step 10: Get the total amount after estimating and updating
        String totalAfterEstimateAndUpdate = cartPage.getTotalAmount();
        // Verify that the total has changed and the shipping cost has been added
        assert !totalBeforeEstimateAndUpdate.equals(totalAfterEstimateAndUpdate) : "Shipping cost is not added to the total.";

        // Step 11: Click "Proceed to Checkout"
        cartPage.processCheckout();

        // switching to new window
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }

        // Step 12a: Enter Billing Information and click Continue
        PCheckOut pCheckOut = new PCheckOut(driver);
        boolean isDropdownPresent = driver.findElements(By.id("billing-address-select")).size() > 0; // Adjust the locator as per your website's structure

        if (isDropdownPresent) {
            // Dropdown for address selection is present, choose "New address"
            pCheckOut.chooseNewAddress(); // Implement a method to select a new address
            pCheckOut.fillBillingInfo("Scioto Mile",
                    "Columbus", "United States", "Ohio", "43001", "0987465213");; // Implement a method to enter new billing information
        }
        pCheckOut.fillBillingInfo("Scioto Mile",
                "Columbus", "United States", "Ohio", "43001", "0987465213");

        try {
            Thread.sleep(5000); // 5000 milliseconds = 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pCheckOut.selectCon1();

        // Step 12b: Enter Shipping Information and click Continue
        try {
            Thread.sleep(5000); // 5000 milliseconds = 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean isDropdownPresent2 = driver.findElements(By.id("shipping-address-select")).size() > 0; // Adjust the locator as per your website's structure

        if (isDropdownPresent2) {
            // Dropdown for address selection is present, choose "New address"
            pCheckOut.chooseNewAddress1(); // Implement a method to select a new address
            pCheckOut.fillShippingInfo("Mikayy","HoangAnh","Comstock Lode",
                    "Carson City", "United States", "Nevada", "88901", "0987775522"); // Implement a method to enter new billing information
        }
        pCheckOut.fillShippingInfo("Mikayy","HoangAnh","Comstock Lode",
                "Carson City", "United States", "Nevada", "88901", "0987775522");
        pCheckOut.selectCon3();

        // Wait for 5 seconds
        try {
            Thread.sleep(5000); // 5000 milliseconds = 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Step 13: In Shipping Method, click Continue
        pCheckOut.selectCon2();

        try {
            Thread.sleep(5000); // 5000 milliseconds = 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Step 14: Select 'Check/Money Order' radio button and click Continue
        pCheckOut.payment();

        try {
            Thread.sleep(5000); // 5000 milliseconds = 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Step 15: Click 'PLACE ORDER' button
        pCheckOut.orderReview();

        // switching to new window
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }

        try {
            Thread.sleep(5000); // 5000 milliseconds = 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Step 16: Verify Order is generated and note the order number
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
            BufferedImage bufferedImage = ImageIO.read(srcFile);
            File destFile = new File("D:\\Study\\SWT301\\selenium-webdriver-java-master\\TestCase06.png"); // Replace with your desired file path
            ImageIO.write(bufferedImage, "png", destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}