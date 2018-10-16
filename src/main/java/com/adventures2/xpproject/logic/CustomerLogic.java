package com.adventures2.xpproject.logic;

import com.adventures2.xpproject.DatabaseConnection;
import com.adventures2.xpproject.base.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class CustomerLogic {
    public static int create(Customer customer) {
        int _id = 0;

        try {
            PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(
                    "INSERT INTO customers (company,name,phone,email,newsmail) VALUES(?, ? , ?, ? ,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, customer.getCompanyName());
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setString(3, customer.getTelephone());
            preparedStatement.setString(4, customer.getEmail());
            preparedStatement.setInt(5, customer.isNewsmail() ? 1 : 0);

            return DatabaseConnection.insertReturnId(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return _id;
    }

    public static HashMap<Integer, Customer> getCustomersFromDatabaseToHashMap() {
        HashMap<Integer, Customer> customerHashMap = new HashMap<>();
        ResultSet resultSet = DatabaseConnection.query("SELECT * FROM customers");
        try {
            while (resultSet.next()) {
                customerHashMap.put(resultSet.getInt("id"), new Customer(
                  resultSet.getInt("id"),
                  resultSet.getString("name"),
                  resultSet.getString("phone"),
                  resultSet.getString("email"),
                  resultSet.getBoolean("newsmail"),
                  resultSet.getString("company")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerHashMap;
    }
}
