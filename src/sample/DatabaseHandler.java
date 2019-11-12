package sample;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler extends Config {
    public Connection getCnct() throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
            System.setOut(new PrintStream(System.out, true, encoding));
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection cnct = DriverManager.getConnection(url, user, password);
            return cnct;

    }
}
