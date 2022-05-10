package automation_test.mortgage_calculator;

import command_providers.ActOn;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page_objects.Home;
import utilities.DateUtils;
import utilities.SqlConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;


public class CalculateMortgageRateParameterized {

    private static final Logger LOGGER = LogManager.getLogger(CalculateMortgageRateParameterized.class);
    WebDriver driver;
    Select select;

    @BeforeMethod
    public void browserInitialization() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        LOGGER.info("----------Test Name: Calculate Monthly Mortgage Rate----------");
        ActOn.browser(driver).openBrowser("https://www.mortgagecalculator.org/");
    }

    @Test
    public void calculateMonthlyPayment() {

        String date = DateUtils.returnNextMonth();
        String[] dateArray = date.split("-");
        String month = dateArray[0];
        String year = dateArray[1];

        try {
            ResultSet rs = SqlConnector.readData("select * from monthly_mortgage");
            while (rs.next()) {
                new Home(driver)
                        .enterHomeValue(rs.getString("homevalue"))
                        .clickOnDollarSign()
                        .enterDownPayment(rs.getString("downpayment"))
                        .enterInterestRate(rs.getString("interestrate"))
                        .enterLoanTerm(rs.getString("loanterm"))
                        .selectStartMonth(month)
                        .enterStartYear(year)
                        .enterPropertyTax(rs.getString("propertytax"))
                        .enterPMI(rs.getString("pmi"))
                        .enterHomeIns(rs.getString("homeownerinsurance"))
                        .enterHOA(rs.getString("monthlyhoa"))
                        .selectLoanType(rs.getString("loantype"))
                        .selectBuyOrRefinance(rs.getString("buyorrefi"))
                        .clickOnCalculate()
                        .validateMonthlyPayment(rs.getString("totalmonthlypayment"));
            }

        } catch (SQLException e) {
            LOGGER.error("SQL Exception is: " + e.getMessage());
        }
    }

    @AfterMethod
    public void closeBrowser(){
        ActOn.browser(driver).closeBrowser();
        LOGGER.info("----------End Test: Calculate Monthly Mortgage Rate----------");

    }
}
