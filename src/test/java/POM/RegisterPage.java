package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage {
    private final WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillRegistrationForm(String firstName, String lastName, String email, String password) {
        WebElement firstNameInput = driver.findElement(By.id("firstname"));
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);

        WebElement lastNameInput = driver.findElement(By.id("lastname"));
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);

        WebElement emailInput = driver.findElement(By.id("email_address"));
        emailInput.clear();
        emailInput.sendKeys(email);

        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.clear();
        passwordInput.sendKeys(password);

        WebElement confirmationInput = driver.findElement(By.id("confirmation"));
        confirmationInput.clear();
        confirmationInput.sendKeys(password);
    }

    public void clickRegisterButton() {
        driver.findElement(By.cssSelector("button[title='Register'] span span")).click();
    }
    public void registerSuccessfully() {
        assert driver.findElement(By.cssSelector("li.success-msg ul li span")).isDisplayed() : "error title is not shown!";
    }
}
