package automation_test.php_travel;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNgWebdriver {
    WebDriver driver;

    //Link Text
    WebElement HomeValue = driver.findElement(By.linkText("Mortgage Calculator"));

    //Partial Link Test
    WebElement HomeValue2 = driver.findElement(By.partialLinkText("Your Mortgage Payment"));


    @BeforeMethod
    public void BrowserInitiation() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        //Launch the Browser
        driver.get("https://phptravels.com/demo");

        //Maximize the Browser
        driver.manage().window().maximize();

        //Wait Statement
        Thread.sleep(5000);

    }

    @Test
    public void VerifyHomePageTitle(){

        //Test Activities
        String expectedResult = "Demo Script Test drive - PHPTRAVELS";
        String actualResult = driver.getTitle();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @AfterMethod
    public void CloseBroswer(){

        //Closing the Browser opened by Selenium
        driver.quit();

    }
}
