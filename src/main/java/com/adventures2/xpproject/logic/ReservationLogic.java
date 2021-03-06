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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class ReservationLogic {
	public static void create(Reservation reservation, Customer customer, HttpSession session)
	{
		int customer_id = 0;

		//If a new customer is made
		if (customer.getId() == 0)
			customer_id = CustomerLogic.create(customer);
		else
			customer_id = customer.getId();

		try {
			PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(
					"INSERT INTO reservations (start,end,customDiscount,peopleAmount,fk_customer_id,fk_activity_id,fk_user_id,fk_employee_id) " +
							"VALUES(?, ?, ?, ?, ?, ?, ?, ?)"
			);
			preparedStatement.setString(1, reservation.getStart());
			preparedStatement.setString(2, reservation.getEnd());
			preparedStatement.setInt(3, reservation.getCustomDiscount());
			preparedStatement.setInt(4, reservation.getPeopleAmount());
			preparedStatement.setInt(5, customer_id);
			preparedStatement.setInt(6, reservation.getFk_activity_id());
			preparedStatement.setInt(7, (int) session.getAttribute("ID"));
			preparedStatement.setInt(8, reservation.getFk_employee_id());

			DatabaseConnection.insert(preparedStatement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Reservation> getReservationsFromDatabaseToArrayList(int activity, String date) {
		ArrayList<Reservation> reservations = new ArrayList<>();

		ResultSet resultSet = null;

		if (!date.equals("") || activity != 0) {
			long unixTimeStart = 0;
			long unixTimeEnd = 0;

			try {
				PreparedStatement preparedStatement = null;

				if (!date.equals("") && activity == 0) {
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Date tempDate = dateFormat.parse(date);
					unixTimeStart = tempDate.getTime() / 1000;
					unixTimeEnd = unixTimeStart + 86400;

					preparedStatement = DatabaseConnection.getConnection().prepareStatement(
							"SELECT * FROM reservations WHERE fk_activity_id = ? OR (start >= ? AND end <= ?)");
					preparedStatement.setInt(1, activity);
					preparedStatement.setLong(2, unixTimeStart);
					preparedStatement.setLong(3, unixTimeEnd);
				}

				if (activity > 0 && !date.equals("")) {
					preparedStatement = DatabaseConnection.getConnection().prepareStatement(
							"SELECT * FROM reservations WHERE fk_activity_id = ? AND (start >= ? AND end <= ?)");
					preparedStatement.setInt(1, activity);
					preparedStatement.setLong(2, unixTimeStart);
					preparedStatement.setLong(3, unixTimeEnd);
				} else if (activity > 0) {
					preparedStatement = DatabaseConnection.getConnection().prepareStatement(
							"SELECT * FROM reservations WHERE fk_activity_id = ? ");
					preparedStatement.setInt(1, activity);
				}
				resultSet = DatabaseConnection.queryWithParameters(preparedStatement);
			} catch (SQLException | ParseException e) {
				e.printStackTrace();
			}
		} else {
			resultSet = DatabaseConnection.query("SELECT * FROM reservations");
		}

		try {
			while (resultSet.next()) {
				reservations.add(new Reservation(
						resultSet.getInt("id"),
						"Kl: " + new SimpleDateFormat("HH:mm").format(
								new Date(Long.parseLong(resultSet.getString("start")) * 1000 - (3600 * 1000))),
						"Kl: " + new SimpleDateFormat("HH:mm").format(
								new Date(Long.parseLong(resultSet.getString("end")) * 1000 - (3600 * 1000))),
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

    public static Reservation getReservationById(int id) {
        try {
            PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM reservations WHERE id = ? ");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = DatabaseConnection.queryWithParameters(preparedStatement);
            if(resultSet.next()){
                return new Reservation(
                        resultSet.getInt(1),
		                new SimpleDateFormat("yyyy-MM-dd HH:mm").format(
				                new Date(Long.parseLong(resultSet.getString("start")) * 1000 - (3600 * 1000))),
		                new SimpleDateFormat("yyyy-MM-dd HH:mm").format(
				                new Date(Long.parseLong(resultSet.getString("end")) * 1000 - (3600 * 1000))),
                        resultSet.getInt(4),
                        resultSet.getInt(5),
                        resultSet.getInt(6),
                        resultSet.getInt(7),
                        resultSet.getInt(8),
                        resultSet.getInt(9)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

	public static void updateReservation(Reservation reservation, int reservation_id) {
		PreparedStatement preparedStatement = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date dateStart = dateFormat.parse(reservation.getStart());
			Date dateEnd = dateFormat.parse(reservation.getEnd());
			long unixTimeStart = dateStart.getTime()/1000 + 3600;
			long unixTimeEnd = dateEnd.getTime()/1000 + 3600;
			preparedStatement = DatabaseConnection.getConnection().prepareStatement("UPDATE reservations  SET start = ?, end =?, " +
					"customDiscount = ?, peopleAmount = ?, fk_customer_id = ?, " +
					"fk_activity_id = ?, fk_user_id = ?, fk_employee_id = ? WHERE reservations.id = ?");
			preparedStatement.setString(1, String.valueOf(unixTimeStart));
			preparedStatement.setString(2, String.valueOf(unixTimeEnd));
			preparedStatement.setInt(3, reservation.getCustomDiscount());
			preparedStatement.setInt(4, reservation.getPeopleAmount());
			preparedStatement.setInt(5, reservation.getFk_customer_id());
			preparedStatement.setInt(6, reservation.getFk_activity_id());
			preparedStatement.setInt(7, reservation.getFk_user_id());
			preparedStatement.setInt(8, reservation.getFk_employee_id());
			preparedStatement.setInt(9, reservation_id);

			preparedStatement.executeUpdate();
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}
	}

	public static void deleteReservation(int reservation_id)
	{
		try
		{
			PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement("DELETE reservations FROM reservations WHERE id = ?");
			preparedStatement.setInt(1, reservation_id);

			DatabaseConnection.delete(preparedStatement);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
