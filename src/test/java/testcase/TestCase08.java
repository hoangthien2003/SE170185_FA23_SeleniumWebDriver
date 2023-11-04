package testcase;

import POM.CartPage;
import POM.LoginPage;
import POM.ReOrderPage;
import driver.driverFactory;
import org.openqa.selenium.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TestCase08 {
    WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = driverFactory.getChromeDriver();
    }

    @Test
    public void testCase08() {
        openWebsite();
        // Click on my account link
        WebElement Account = driver.findElement(By.xpath("//span[normalize-space()='Account']"));
        Account.click();
        WebElement myAccountLink = driver.findElement(By.cssSelector("div[id='header-account'] a[title='My Account']"));
        myAccountLink.click();

        // Login using previously created credential
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillLoginForm("hihi1221@gmail.com", "123456");
        loginPage.clickLogin();

        // Click on ReOrder
        WebElement reOrder = driver.findElement(By.xpath("//*[@id=\"my-orders-table\"]/tbody/tr/td[6]/span/a[2]"));
        reOrder.click();

        // Change QTY, click update
        CartPage cartPage = new CartPage(driver);
        String grandTotalOld = cartPage.getTotalAmount();
        cartPage.changeQTY();
        String grandTotalNew = cartPage.getTotalAmount();

        // Verify grandTotal is changed
        assert !grandTotalNew.equals(grandTotalOld) : "Grand total doesn't changed!";

        // Complete billing and shipping information
        ReOrderPage reOrderPage = new ReOrderPage(driver);
        reOrderPage.completeReOrder(driver, cartPage);

        // Verify order is generated and note the order number
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
            BufferedImage bufferedImage = ImageIO.read(srcFile);
            File destFile = new File("D:\\Study\\SWT301\\selenium-webdriver-java-master\\TestCase08.png"); // Replace with your desired file path
            ImageIO.write(bufferedImage, "png", destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openWebsite() {
        driver.get("http://live.techpanda.org/");
    }
}
