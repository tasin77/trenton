package playground;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import parameters.DataProviderClass;

public class TestNgMavenTest {

    private static final Logger LOGGER  = LogManager.getLogger(TestNgMavenTest.class);

    @Test(dataProvider = "MultipleValue", dataProviderClass = DataProviderClass.class)
    public void run(String name, String state, int zipCode) {
        System.out.println("Name is: " + name);
        System.out.println("State is: " + state);
        System.out.println("ZipCode is: " + zipCode);
    }

}
