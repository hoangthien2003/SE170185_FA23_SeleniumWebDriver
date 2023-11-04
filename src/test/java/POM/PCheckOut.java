package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class PCheckOut {
    private final WebDriver driver;

    public PCheckOut(WebDriver driver) {
        this.driver = driver;
    }

    public void fillBillingInfo(String address, String city, String country, String state, String zip, String telephone){
//        WebElement firstNameInput = driver.findElement(By.id("billing:firstname"));
//        firstNameInput.clear();
//        firstNameInput.sendKeys(firstName);
//
//        WebElement lastNameInput = driver.findElement(By.id("billing:lastname"));
//        lastNameInput.clear();
//        lastNameInput.sendKeys(lastName);

        WebElement addressInput = driver.findElement(By.id("billing:street1"));
        addressInput.clear();
        addressInput.sendKeys(address);

        WebElement cityInput = driver.findElement(By.id("billing:city"));
        cityInput.clear();
        cityInput.sendKeys(city);

        Select countryDropdown = new Select(driver.findElement(By.id("billing:country_id")));
        countryDropdown.selectByVisibleText(country);

        if ("United States".equals(country)) {
            // Select the region from the dropdown for United States
            Select regionDropdown = new Select(driver.findElement(By.id("billing:region_id")));
            regionDropdown.selectByVisibleText(state);
        } else {
            // Fill in the REGION input field for other countries
            WebElement regionInput = driver.findElement(By.id("billing:region_id"));
            regionInput.sendKeys(state);
        }

        // Fill in the ZIP code
        WebElement zipInput = driver.findElement(By.id("billing:postcode"));
        zipInput.clear();
        zipInput.sendKeys(zip);

        WebElement teleInput = driver.findElement(By.id("billing:telephone"));
        teleInput.clear();
        teleInput.sendKeys(telephone);

        driver.findElement(By.xpath("//label[@for='billing:use_for_shipping_no']")).click();
    }

    public void fillShippingInfo(String firstName,String lastName,String address, String city, String country, String state, String zip, String telephone){
        WebElement firstNameInput = driver.findElement(By.id("shipping:firstname"));
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);

        WebElement lastNameInput = driver.findElement(By.id("shipping:lastname"));
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);

        WebElement addressInput = driver.findElement(By.id("shipping:street1"));
        addressInput.clear();
        addressInput.sendKeys(address);

        WebElement cityInput = driver.findElement(By.id("shipping:city"));
        cityInput.clear();
        cityInput.sendKeys(city);

        Select countryDropdown = new Select(driver.findElement(By.id("shipping:country_id")));
        countryDropdown.selectByVisibleText(country);

        if ("United States".equals(country)) {
            // Select the region from the dropdown for United States
            Select regionDropdown = new Select(driver.findElement(By.id("shipping:region_id")));
            regionDropdown.selectByVisibleText(state);
        } else {
            // Fill in the REGION input field for other countries
            WebElement regionInput = driver.findElement(By.id("shipping:region_id"));
            regionInput.sendKeys(state);
        }

        // Fill in the ZIP code
        WebElement zipInput = driver.findElement(By.id("shipping:postcode"));
        zipInput.clear();
        zipInput.sendKeys(zip);

        WebElement teleInput = driver.findElement(By.id("shipping:telephone"));
        teleInput.clear();
        teleInput.sendKeys(telephone);
    }

    public void selectCon1(){
        driver.findElement(By.cssSelector("button[onclick='billing.save()'] span span")).click();
    }

    public void selectCon2(){
        driver.findElement(By.xpath("//button[@onclick='shippingMethod.save()']")).click();
    }

    public void selectCon3(){
        driver.findElement(By.xpath("//button[@onclick='shipping.save()']")).click();
    }

    public void payment(){
        driver.findElement(By.cssSelector("label[for='p_method_checkmo']")).click();
        driver.findElement(By.cssSelector("button[onclick='payment.save()'] span span")).click();
    }

    public void orderReview(){
        driver.findElement(By.cssSelector("button[title='Place Order']")).click();
    }

    public String getOrderNumber() {
        driver.findElement(By.cssSelector("a[href='http://live.techpanda.org/index.php/sales/order/view/order_id/20418/']")).click(); // Adjust the locator as per your website's structure
        // switching to new window
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
        WebElement orderNumberElement = driver.findElement(By.cssSelector("div[class='page-title title-buttons'] h1"));
        return orderNumberElement.getText();
    }

    public void chooseNewAddress() {
        Select addressDropdown = new Select(driver.findElement(By.id("billing-address-select")));
        addressDropdown.selectByVisibleText("New Address");
    }

    public void chooseNewAddress1() {
        Select addressDropdown = new Select(driver.findElement(By.id("shipping-address-select")));
        addressDropdown.selectByVisibleText("New Address");
    }
}