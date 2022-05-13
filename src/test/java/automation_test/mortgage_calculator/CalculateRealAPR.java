package automation_test.mortgage_calculator;

import org.testng.annotations.Test;
import page_objects.Home;

public class CalculateRealAPR extends BaseClass {
    @Test
    public void calculateRealAprRate() {
        new Home(driver)
                .mouseHoverToRates()
                .navigateToRealApr()
                .waitForPageToLoad()
                .enterHomePrice("200000")
                .selectDownPaymentInDollar()
                .enterDownPayment("15000")
                .enterInterestRate("3")
                .clickOnCalculateButton()
                .validateAprRate("3.130%");
    }
}
