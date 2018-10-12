package com.adventures2.xpproject.logic;

import com.adventures2.xpproject.DatabaseConnection;
import com.adventures2.xpproject.base.Customer;
import com.adventures2.xpproject.base.Reservation;

import javax.servlet.http.HttpSession;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReservationLogic {
    public static void create(Reservation reservation, Customer customer, HttpSession session) {
        int id = 0;

        //If a new customer is made
        if(customer.getId() == 0)
            id = CustomerLogic.create(customer);

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = DatabaseConnection.getConnection().prepareStatement(
                    "INSERT INTO reservations (start,end,customDiscount,peopleAmount,fk_customer_id,fk_activity_id,fk_user_id) VALUES(?, ?, ?, ?, ?, ?, ?)"
            );
            preparedStatement.setString(1, reservation.getStart());
            preparedStatement.setString(2, reservation.getEnd());
            preparedStatement.setInt(3, reservation.getCustomDiscount());
            preparedStatement.setInt(4, reservation.getPeopleAmount());
            preparedStatement.setInt(5, id);
            preparedStatement.setInt(6, reservation.getFk_activity_id());
            preparedStatement.setInt(7, (int)session.getAttribute("ID"));

            DatabaseConnection.insert(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}