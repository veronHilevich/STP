package sample;

import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

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
      //  Statement stmt = getCnct().createStatement();
        PreparedStatement preparedStatement = null;
        preparedStatement = getCnct().prepareStatement("SELECT position FROM employees where full_name=?");
        preparedStatement.setString(1, fullName);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            return resultSet.getString("position");
        }
        return resultSet.getString("position");
    }
    public void infoInListView(ListView<String> listOfContracts) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        Statement stmt = getCnct().createStatement();
        ResultSet resultSet = stmt.executeQuery("SELECT contract_number,to_char(date_of_conclusion,'DD/MM/YYYY'),client_information,to_char(date_of_Expiry,'DD/MM/YYYY'),cost_of_work FROM contracts");
        while (resultSet.next()) {
            String contract_number = Integer.toString(resultSet.getInt("contract_number"));
            String date_of_conclusion = resultSet.getString("to_char(date_of_conclusion,'DD/MM/YYYY')");
            String client_information = resultSet.getString("client_information");
            String date_of_Expiry = resultSet.getString("to_char(date_of_Expiry,'DD/MM/YYYY')");
            String cost_of_work = Float.toString(resultSet.getFloat("Cost_of_work"));
            listOfContracts.getItems().addAll(contract_number + "        " + date_of_conclusion + "           " + date_of_Expiry + "        " + client_information + "       " + cost_of_work);
            listOfContracts.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        }
    }
}
