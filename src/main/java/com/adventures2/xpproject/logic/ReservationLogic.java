package com.adventures2.xpproject.logic;

import com.adventures2.xpproject.DatabaseConnection;
import com.adventures2.xpproject.base.Activity;
import com.adventures2.xpproject.base.Customer;
import com.adventures2.xpproject.base.Employee;
import com.adventures2.xpproject.base.Reservation;

import javax.servlet.http.HttpSession;
import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationLogic {
    public static void create(Reservation reservation, Customer customer, Employee employee, HttpSession session) {
        int id = 0;

        //If a new customer is made
        if(customer.getId() == 0)
            id = CustomerLogic.create(customer);
        else
            id = customer.getId();

        try {
            PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(
                    "INSERT INTO reservations (start,end,customDiscount,peopleAmount,fk_customer_id,fk_activity_id,fk_user_id,fk_employee_id) " +
                            "VALUES(?, ?, ?, ?, ?, ?, ?, ?)"
            );
            preparedStatement.setString(1, reservation.getStart());
            preparedStatement.setString(2, reservation.getEnd());
            preparedStatement.setInt(3, reservation.getCustomDiscount());
            preparedStatement.setInt(4, reservation.getPeopleAmount());
            preparedStatement.setInt(5, id);
            preparedStatement.setInt(6, reservation.getFk_activity_id());
            preparedStatement.setInt(7, (int)session.getAttribute("ID"));
            preparedStatement.setInt(8, employee.getId());

            DatabaseConnection.insert(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Reservation> getReservationsFromDatabaseToArrayList() {
        ArrayList<Reservation> reservations = new ArrayList<>();
        ResultSet resultSet = DatabaseConnection.query(  "SELECT * FROM reservations");

        try {
            while (resultSet.next()) {
                reservations.add(new Reservation(
                        resultSet.getInt("id"),
                        resultSet.getString("start"),
                        resultSet.getString("end"),
                        resultSet.getInt("customDiscount"),
                        resultSet.getInt("peopleAmount"),
                        resultSet.getInt("fk_customer_id"),
                        resultSet.getInt("fk_activity_id"),
                        resultSet.getInt("fk_user_id"),
                       resultSet.getInt("fk_employee_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;
    }
}
