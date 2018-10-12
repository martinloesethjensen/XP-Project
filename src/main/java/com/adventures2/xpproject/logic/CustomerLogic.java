package com.adventures2.xpproject.logic;

import com.adventures2.xpproject.DatabaseConnection;
import com.adventures2.xpproject.base.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerLogic {
    public static int create(Customer customer) {
        int _id = 0;

        try {
            PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(
                    "INSERT INTO customers (company,name,phone,email,newspaper) VALUES(?, ? , ?, ? ,?)");
            preparedStatement.setString(1, customer.getCompanyName());
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setString(3, customer.getTelephone());
            preparedStatement.setString(4, customer.getEmail());
            preparedStatement.setBoolean(5, customer.isNewsmail());

            return DatabaseConnection.insertReturnId(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return _id;
    }
}
