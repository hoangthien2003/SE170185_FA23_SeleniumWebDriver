package testcase;

import POM.LoginPage;
import POM.RegisterPage;
import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCase07 {
    WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = driverFactory.getChromeDriver();
    }

    @Test
    public void testCase07() {
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

        // Click on My Orders
        WebElement myOrder = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[1]/div/div[2]/ul/li[4]/a"));
        myOrder.click();

        // Click on View Orders
        WebElement viewOrder = driver.findElement(By.xpath("//*[@id=\"my-orders-table\"]/tbody/tr/td[6]/span/a[1]"));
        viewOrder.click();

        // Click on Print Order
        WebElement printOrder = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div/div[1]/a[2]"));
        printOrder.click();

        assert true;
    }

    private void openWebsite() {
        driver.get("http://live.techpanda.org/");
    }
}
