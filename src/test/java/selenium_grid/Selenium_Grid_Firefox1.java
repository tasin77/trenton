package selenium_grid;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import utilities.DriverFactory;

public class Selenium_Grid_Firefox1 {
    @Test
    public void executeInAwsDocker() {
        WebDriver driver = DriverFactory.getInstance().getDriver();
        driver.get("https://amazon.com");
        System.out.println(driver.getTitle());
        driver.quit();
    }
}
