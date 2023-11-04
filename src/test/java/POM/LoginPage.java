package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillLoginForm(String email, String password){
        WebElement emailInput = driver.findElement(By.id("email"));
        emailInput.clear();
        emailInput.sendKeys(email);

        WebElement passwordInput = driver.findElement(By.id("pass"));
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(By.cssSelector("#send2")).click();
    }
}
