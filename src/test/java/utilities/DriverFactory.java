package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

    //Private Constructor - Do not allow to initialize this class from outside
    private DriverFactory() {

    }

    private static final DriverFactory instance = new DriverFactory();

    public static DriverFactory getInstance() {
        return instance;
    }

    // Thread Local Driver Object for WebDriver
    ThreadLocal<WebDriver> driver = ThreadLocal.withInitial(() -> {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    });

    //Call this method to get Driver Object and Launch Browser
    public WebDriver getDriver() {
        return driver.get();
    }

    //Quits the Driver Object and closes the browser
    public void removeDriver()
    {
        driver.get().quit();
        driver.remove();
    }
}
