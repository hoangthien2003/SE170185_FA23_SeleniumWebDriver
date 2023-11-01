package testcase;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class TestCase03 {
    WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = driverFactory.getChromeDriver();
    }

    @Test
    public void testAddingProductToCartAndVerifyingErrorMessage() {
        openWebsite();
        clickOnMobileCategory();
        addSonyXperiaToCart();
        updateQuantityTo1000AndVerifyErrorMessage();
        emptyCartAndVerifyEmptyMessage();
    }

    private void openWebsite() {
        driver.get("http://live.techpanda.org/");
    }

    private void clickOnMobileCategory() {
        WebElement mobileElement = driver.findElement(By.cssSelector("li.level0.nav-1.first"));
        mobileElement.click();
    }

    private void addSonyXperiaToCart() {
        List<WebElement> productInfo = driver.findElements(By.cssSelector("li.item.last div.product-info"));
        for (WebElement productItem : productInfo) {
            WebElement productName = productItem.findElement(By.cssSelector("h2.product-name a"));
            if (productName.getAttribute("title").equals("Sony Xperia")) {
                WebElement addToCartButton = productItem.findElement(By.cssSelector("div.product-info button[title='Add to Cart']"));
                addToCartButton.click();
                break;
            }
        }
    }

    private void updateQuantityTo1000AndVerifyErrorMessage() {
        WebElement inputQuantity = driver.findElement(By.cssSelector("td.product-cart-actions input.input-text.qty"));
        WebElement buttonQuantity = driver.findElement(By.cssSelector("td.product-cart-actions button.button.btn-update"));
        inputQuantity.click();
        inputQuantity.clear();
        inputQuantity.sendKeys("1000");
        buttonQuantity.click();

        WebElement messageError = driver.findElement(By.cssSelector("td.product-cart-info p.item-msg.error"));
        if (messageError.isDisplayed()) {
            String expectedError = "\"The requested quantity for \"Sony Xperia\" is not available.";
            String actualError = messageError.getText();
            assert actualError.equals(expectedError) : "Wrong error message!";
        }
    }

    private void emptyCartAndVerifyEmptyMessage() {
        WebElement emptyButton = driver.findElement(By.cssSelector("button#empty_cart_button"));
        emptyButton.click();
        WebElement emptyMessage = driver.findElement(By.cssSelector("div.page-title h1"));
        assert emptyMessage.isDisplayed() : "Empty message unavailable!";
    }
}
