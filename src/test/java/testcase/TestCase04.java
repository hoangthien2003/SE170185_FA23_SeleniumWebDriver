package testcase;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class TestCase04 {
    WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = driverFactory.getChromeDriver();
    }

    @Test
    public void testCase04() {
        openWebsite();
        clickOnMobileCategory();
        addSonyXperiaToCompare();
        addIphoneToCompare();
        compareProduct();
    }

    private void openWebsite() {
        driver.get("http://live.techpanda.org/");
    }

    private void clickOnMobileCategory() {
        WebElement mobileElement = driver.findElement(By.cssSelector("li.level0.nav-1.first"));
        mobileElement.click();
    }

    private void addSonyXperiaToCompare() {
        List<WebElement> productInfo = driver.findElements(By.cssSelector("li.item.last div.product-info"));
        for (WebElement productItem : productInfo) {
            WebElement productName = productItem.findElement(By.cssSelector("h2.product-name a"));
            if (productName.getAttribute("title").equals("Sony Xperia")) {
                WebElement addToCompareButton = productItem.findElement(By.cssSelector("div.product-info a.link-compare"));
                addToCompareButton.click();
                break;
            }
        }
    }

    private void addIphoneToCompare() {
        List<WebElement> productInfo = driver.findElements(By.cssSelector("li.item.last div.product-info"));
        for (WebElement productItem : productInfo) {
            WebElement productName = productItem.findElement(By.cssSelector("h2.product-name a"));
            if (productName.getAttribute("title").equals("IPhone")) {
                WebElement addToCompareButton = productItem.findElement(By.cssSelector("div.product-info a.link-compare"));
                addToCompareButton.click();
                break;
            }
        }
    }

    private void compareProduct() {
        WebElement compareButton = driver.findElement(By.cssSelector("div.col-right.sidebar button[title='Compare']"));
        compareButton.click();
        String parentWindowHandler = driver.getWindowHandle(); // Store your parent window
        String subWindowHandler = null;

        Set<String> handles = driver.getWindowHandles(); // get all window handles
        Iterator<String> iterator = handles.iterator();
        while (iterator.hasNext()){
            subWindowHandler = iterator.next();
        }
        driver.switchTo().window(subWindowHandler); // switch to popup window
        WebElement heading = driver.findElement(By.cssSelector("div.page-title.title-buttons h1"));
        if (heading.isDisplayed()) {
            boolean flag = true;
            WebElement sonyXperiaName = driver.findElement(By.cssSelector("tr.product-shop-row.top.first.odd td h2.product-name a"));
            if (!sonyXperiaName.getAttribute("title").equals("Sony Xperia")) flag = false;
            WebElement iPhoneName = driver.findElement(By.cssSelector("tr.product-shop-row.top.first.odd td.last h2.product-name a"));
            if (!iPhoneName.getAttribute("title").equals("IPhone")) flag = false;
            assert flag == true : "Error compare!";
        }
        assert heading.isDisplayed() : "Title compare is not shown!";
        WebElement closeWindowButton = driver.findElement(By.cssSelector("div.buttons-set button[title='Close Window']"));
        closeWindowButton.click();
    }
}
