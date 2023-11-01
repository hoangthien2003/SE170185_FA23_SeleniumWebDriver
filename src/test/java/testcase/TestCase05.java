package testcase;

import POM.RegisterPage;
import driver.driverFactory;
import org.openqa.selenium.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TestCase05 {
    WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = driverFactory.getChromeDriver();
    }

    @Test
    public void testCase05() {
        openWebsite();
        goToCreateAccount();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.fillRegistrationForm("hihi", "hihi", "hihi1221@gmail.com", "123456");
        registerPage.clickRegisterButton();
        registerPage.registerSuccessfully();
        addProductAndShareWishlist();
        checkWishlistIsShared();
    }

    private void openWebsite() {
        driver.get("http://live.techpanda.org/");
    }

    private void goToCreateAccount() {
        WebElement Account = driver.findElement(By.xpath("//span[normalize-space()='Account']"));
        Account.click();
        WebElement myAccountButton = driver.findElement(By.xpath("//a[@title='My Account']"));
        myAccountButton.click();
        WebElement createAccountButton = driver.findElement(By.xpath("//*[@id=\"login-form\"]/div/div[1]/div[2]/a"));
        createAccountButton.click();
    }

    private void addProductAndShareWishlist() {
        // Go to TV Menu
        driver.findElement(By.xpath("//*[@id=\"nav\"]/ol/li[2]/a")).click();
        // Add product (e.g., LG LCD) to your wish list
        driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div[1]/div[2]/ul/li[1]/div/div[3]/ul/li[1]/a")).click();
        // Click button share wishlist
        driver.findElement(By.xpath("//span[contains(text(),'Share Wishlist')]")).click();
        // Fill email, message and click share
        driver.findElement(By.id("email_address")).sendKeys("friend@example.com");
        driver.findElement(By.id("message")).sendKeys("Check out my wishlist!");
        driver.findElement(By.cssSelector("button[title='Share Wishlist'] span span")).click();
    }

    private void checkWishlistIsShared() {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
            BufferedImage bufferedImage = ImageIO.read(srcFile);
            File destFile = new File("D:\\Study\\SWT301\\selenium-webdriver-java-master\\TestCase06.png");
            ImageIO.write(bufferedImage, "png", destFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
