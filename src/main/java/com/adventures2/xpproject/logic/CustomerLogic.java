package com.adventures2.xpproject.logic;

import com.adventures2.xpproject.DatabaseConnection;
import com.adventures2.xpproject.base.Customer;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

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
}
