package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.NoSuchElementException;

public class CartPage {
    private WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void estimateInfo(String country, String region, String zip) {
        // Select the country from the dropdown
        Select countryDropdown = new Select(driver.findElement(By.id("country")));
        countryDropdown.selectByVisibleText(country);

        if ("United States".equals(country)) {
            // Select the region from the dropdown for United States
            Select regionDropdown = new Select(driver.findElement(By.id("region_id")));
            regionDropdown.selectByVisibleText(region);
        } else {
            // Fill in the REGION input field for other countries
            WebElement regionInput = driver.findElement(By.id("region_id"));
            regionInput.sendKeys(region);
        }

        // Fill in the ZIP code
        WebElement zipInput = driver.findElement(By.id("postcode"));
        zipInput.sendKeys(zip);
    }

    public String getTotalAmount() {
        WebElement totalElement = driver.findElement(By.cssSelector("strong span[class='price']")); // Adjust the locator as per your website's structure
        return totalElement.getText();
    }

    public void estimateShippingCost() {
        driver.findElement(By.cssSelector("button[title='Estimate'] span span")).click();
    }

    public boolean isShippingCostGenerated() {
        try {
            WebElement shippingCostElement = driver.findElement(By.xpath("//label[@for='s_method_flatrate_flatrate']")); // Adjust the locator as per your website's structure
            return shippingCostElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return false; // Shipping cost element not found
        }
    }

    public void selectShippingCost() {
        WebElement shippingCostRadioButton = driver.findElement(By.cssSelector("label[for='s_method_flatrate_flatrate'] span[class='price']"));
        if (!shippingCostRadioButton.isSelected()) {
            shippingCostRadioButton.click();
        }
    }

    public void updateTotal() {
        driver.findElement(By.cssSelector("button[title='Update Total'] span span")).click();
    }

    public void processCheckout(){
        driver.findElement(By.cssSelector("li[class='method-checkout-cart-methods-onepage-bottom'] button[title='Proceed to Checkout'] span span")).click();
    }

    public void changeQTY(){
        WebElement qtyInput = driver.findElement(By.cssSelector("input[title='Qty']"));
        qtyInput.clear();
        qtyInput.sendKeys("4");
        WebElement updateButton = driver.findElement(By.cssSelector("button[title='Update'] span span"));
        updateButton.click();
    }
}