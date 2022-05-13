package automation_test.mortgage_calculator;

import org.testng.annotations.Test;
import page_objects.Home;
import utilities.DateUtils;

public class CalculateMortgageRate extends BaseClass {
    @Test
    public void calculateMonthlyPayment() {
        String date = DateUtils.returnNextMonth();
        String[] dateArray = date.split("-");
        String month = dateArray[0];
        String year = dateArray[1];

        new Home(driver)
                .enterHomeValue("300000")
                .clickOnDollarSign()
                .enterDownPayment("60000")
                .enterInterestRate("3")
                .enterLoanTerm("30")
                .selectStartMonth(month)
                .enterStartYear(year)
                .enterPropertyTax("5000")
                .enterPMI("0.5")
                .enterHomeIns("1000")
                .enterHOA("100")
                .selectLoanType("FHA")
                .selectBuyOrRefinance("Buy")
                .clickOnCalculate()
                .validateMonthlyPayment("1,611.85");
    }
}
