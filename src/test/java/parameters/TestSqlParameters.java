package parameters;

import org.testng.annotations.Test;
import utilities.SqlConnector;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestSqlParameters {
    @Test
    public void testDatabaseValues() {
        try {
            ResultSet rs = SqlConnector.readData("select * from monthly_mortgage");
            while (rs.next()) {
                System.out.println("The Home Price is: " + rs.getString("homevalue"));
                System.out.println("The Loan Amount is: " + rs.getString("loanamount"));
            }
        } catch (SQLException e) {
            System.out.println("Exception is: " + e.getMessage());
        }
    }
}
