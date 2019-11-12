package sample;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.sql.*;

public class DatabaseHandler extends Config {
    public Connection getCnct() throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        System.setOut(new PrintStream(System.out, true, encoding));
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection cnct = DriverManager.getConnection(url, user, password);
        return cnct;

    }

    public String getPosition(String fullName) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        Statement stmt = getCnct().createStatement();
        PreparedStatement preparedStatement = null;
        preparedStatement = getCnct().prepareStatement("SELECT position FROM employees where full_name=?");
        preparedStatement.setString(1, fullName);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            return resultSet.getString("position");
        }
        return resultSet.getString("position");
    }
}
