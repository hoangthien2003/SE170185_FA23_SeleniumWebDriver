package testcase;

import driver.driverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCase05 {
    WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = driverFactory.getChromeDriver();
    }

    @Test
    public void testCase04() {
        openWebsite();
    }

    private void openWebsite() {
        driver.get("http://live.techpanda.org/");
    }
}
