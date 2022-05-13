package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class SqlConnector {
    private static String dbName = ReadConfigFiles.getPropertyValues("DbName");
    private static final String url = "jdbc:postgresql://localhost:5432/" + dbName;
    private static final String User = ReadConfigFiles.getPropertyValues("DbUser");
    private static final String Password = ReadConfigFiles.getPropertyValues("DbPassword");

    private static final Logger LOGGER = LogManager.getLogger(SqlConnector.class);

    private static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, User, Password);
            LOGGER.info("Successfully Connected to the Database");
        } catch (SQLException e) {
            LOGGER.error("SQL Connection Exception" + e.getMessage());
        }
        return conn;
    }

    public static ResultSet readData(String SQL) throws SQLException {
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = connect();
            LOGGER.debug("Connection Object Value: " + conn);

            Statement stat = conn.createStatement();
            rs = stat.executeQuery(SQL);
        } catch (SQLException e) {
            LOGGER.error("SQL Result Set Exception is: " + e.getMessage());
        } finally {
            conn.close();
        }
        return rs;
    }

}
