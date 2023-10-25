package testcase;

import driver.driverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TestCase01 {
    @Test
    public void testCase01() {
        WebDriver driver = driverFactory.getChromeDriver();
        driver.get("http://live.techpanda.org/");
        WebElement titleElement = driver.findElement(By.cssSelector("h2:nth-child(1)"));
        String title = titleElement.getText();
        String expectedTitle = "THIS IS DEMO SITE FOR   ";
        try {
            assert title.equals(expectedTitle) : "Title doesn't match!";
        } catch(Exception e) {
            e.printStackTrace();
        }
        // Step 3. Click on -> MOBILE -> menu
        WebElement mobileMenu = driver.findElement(By.xpath("//a[normalize-space()='Mobile']"));
        mobileMenu.click();

        // Step 4. In the list of all mobile, select SORT BY -> dropdown as name
        WebElement sortByDropdown = driver.findElement(By.cssSelector("body > div:nth-child(1) > div:nth-child(2) > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > select:nth-child(2)"));
        Select select = new Select(sortByDropdown);
        select.selectByVisibleText("Name");

        // Step 5. Verify all products are sorted by name
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
            BufferedImage bufferedImage = ImageIO.read(srcFile);
            File destFile = new File("D:\\Study\\SWT301\\selenium-webdriver-java-master\\TestCase01.png"); // Replace with your desired file path
            ImageIO.write(bufferedImage, "png", destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
