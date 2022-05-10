package automation_test.mortgage_calculator;

import command_providers.ActOn;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page_objects.Home;
import parameters.DataProviderClass;

import java.time.Duration;

public class CalculateRealAPRParameterized {
    private static final Logger LOGGER = LogManager.getLogger(CalculateRealAPRParameterized.class);
    WebDriver driver;

    @BeforeMethod
    public void browserInitialization() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        ActOn.browser(driver).openBrowser("https://www.mortgagecalculator.org/");
    }

    @Test(dataProvider = "RealApr", dataProviderClass = DataProviderClass.class)
    public void calculateRealAprRate(String homePrice, String downPayment, String interestRate, String expectedRealApr) {
        new Home(driver)
                .mouseHoverToRates()
                .navigateToRealApr()
                .waitForPageToLoad()
                .enterHomePrice(homePrice)
                .selectDownPaymentInDollar()
                .enterDownPayment(downPayment)
                .enterInterestRate(interestRate)
                .clickOnCalculateButton()
                .validateAprRate(expectedRealApr);
    }

    @AfterMethod
    public void closeBrowser(){
        ActOn.browser(driver).closeBrowser();
    }
}
