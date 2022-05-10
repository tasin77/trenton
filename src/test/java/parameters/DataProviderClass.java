package parameters;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderClass {

    @DataProvider(name = "SingleValue")
    public Object[][] storeSingleValue() {
        return new Object[][] {
                {"Seyum"},
                {"Karim"},
                {"Minar"}
        };
    }

    @DataProvider(name = "MultipleValue")
    public Object[][] StoreMultipleValues() {
        return new Object[][] {
                {"Seyum", "New York", 11235},
                {"Karim", "Delaware", 23456},
                {"Minar", "Pennsylvania", 45678}
        };
    }

    @Test(dataProvider = "SingleValue")
    public void readSingleValue(String name) {
        System.out.println("Name is: " + name);
    }

    @Test(dataProvider = "MultipleValue")
    public void readMultipleValue(String name, String state, int zipCode) {
        System.out.println("Name is: " + name);
        System.out.println("State is: " + state);
        System.out.println("ZipCode is: " + zipCode);
    }

    @DataProvider(name = "RealApr")
    public Object[][] RealAprData() {
        return new Object[][] {
                {"200000", "15000", "3", "3.130%"},
                {"250000", "17000", "3.2", "3.321%"}
        };
    }
}
