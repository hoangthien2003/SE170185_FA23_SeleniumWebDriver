package testcase;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class TestCase02 {
    @Test
    public void testCase02() {
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

        //2. Click on -> MOBILE menu
        WebElement mobileMenu = driver.findElement(By.xpath("//a[normalize-space()='Mobile']"));
        mobileMenu.click();

        //3. In the list of all mobile , read the cost of Sony Xperia mobile (which is $100)
        WebElement sonyXperiaCost = driver.findElement(By.xpath("//span[contains(text(),'$100.00')]"));
        String sonyXperiaPrice = sonyXperiaCost.getText();

        //4. Click on Sony Xperia mobile
        WebElement sonyXperiaLink = driver.findElement(By.xpath("//a[@title='Sony Xperia']"));
        sonyXperiaLink.click();
        //5. Read the Sony Xperia mobile from detail page.
        WebElement sonyXperiaDetailPrice = driver.findElement(By.xpath("//span[@class='price']"));
        String sonyXperiaDetailPriceText = sonyXperiaDetailPrice.getText();

        //6. Compare Product value in list and details page should be equal ($100).
        assert sonyXperiaPrice.equals(sonyXperiaDetailPriceText) : "Price of product doesn't match!";
        if (sonyXperiaPrice.equals(sonyXperiaDetailPriceText)) {
            System.out.println("Price of products are equal.");
        }
    }
}
