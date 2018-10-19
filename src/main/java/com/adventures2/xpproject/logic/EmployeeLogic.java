package com.adventures2.xpproject.logic;

import com.adventures2.xpproject.DatabaseConnection;
import com.adventures2.xpproject.base.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class EmployeeLogic {
    public static HashMap<Integer, Employee> getEmployeesFromDatabaseToHashMap() {
        HashMap<Integer, Employee> employeeHashMap = new HashMap<>();
        ResultSet resultSet = DatabaseConnection.query("SELECT * FROM employees");

        try {
            while (resultSet.next()) {
                employeeHashMap.put(
                        resultSet.getInt("id"),
                        new Employee(
                                resultSet.getInt("id"),
                                resultSet.getString("realname")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employeeHashMap;
    }

    public static ArrayList<Employee> getEmployeesForScheme() {
        ArrayList<Employee> employees = new ArrayList<>();
        ResultSet resultSet = DatabaseConnection.query("SELECT * FROM employees");

        try {
            while (resultSet.next()) {
                employees.add(new Employee(
                  resultSet.getString("realname"),
                  resultSet.getInt("monday") == 1,
                  resultSet.getInt("tuesday") == 1,
                  resultSet.getInt("wednesday") == 1,
                  resultSet.getInt("thursday") == 1,
                  resultSet.getInt("friday") == 1
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }
}
