package selenium_grid;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import utilities.DriverFactory;

public class Selenium_Grid_Chrome1 {
    @Test
    public void executeInAwsDocker() {
        WebDriver driver = DriverFactory.getInstance().getDriver();
        driver.get("https://mortgagecalculator.org");
        System.out.println(driver.getTitle());
        driver.quit();
    }
}
