package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ReOrderPage {
    private WebDriver driver;

    public ReOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void completeReOrder(WebDriver driver, CartPage reorderPage) {
        String totalBeforeEstimateAndUpdate = reorderPage.getTotalAmount();
        // Step 6: Enter general shipping country, state/province, and zip for the shipping cost estimate
        reorderPage.estimateInfo("United States", "Ohio", "43001");

        // Step 7: Estimate and update shipping costs
        reorderPage.estimateShippingCost();

        // Step 8: Verify Shipping cost generated
        boolean isShippingCostGenerated = reorderPage.isShippingCostGenerated();
        assert isShippingCostGenerated : "Shipping cost is not generated.";
        // Step 9: Select Shipping Cost, Update Total
        reorderPage.selectShippingCost();
        reorderPage.updateTotal();

        // Step 10: Get the total amount after estimating and updating
        String totalAfterEstimateAndUpdate = reorderPage.getTotalAmount();
        // Verify that the total has changed and the shipping cost has been added
        assert !totalBeforeEstimateAndUpdate.equals(totalAfterEstimateAndUpdate) : "Shipping cost is not added to the total.";

        // Step 11: Click "Proceed to Checkout"
        reorderPage.processCheckout();

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
                    "Columbus", "United States", "Ohio", "43001", "0987465213");
            ; // Implement a method to enter new billing information
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
            pCheckOut.fillShippingInfo("Mikayy", "HoangAnh", "Comstock Lode",
                    "Carson City", "United States", "Nevada", "88901", "0987775522"); // Implement a method to enter new billing information
        }
        pCheckOut.fillShippingInfo("Mikayy", "HoangAnh", "Comstock Lode",
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

        try {
            Thread.sleep(5000); // 5000 milliseconds = 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
