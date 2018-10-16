package com.adventures2.xpproject.service;

import com.adventures2.xpproject.DatabaseConnection;
import com.adventures2.xpproject.base.Activity;
import com.adventures2.xpproject.base.Customer;
import com.adventures2.xpproject.base.Employee;
import com.adventures2.xpproject.base.Reservation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class HandleDataFromDB {

	static final String SELECT_ALL_FROM_TABLE = "SELECT * FROM ";

	private HashMap<Integer, Employee> employeeHashMap = new HashMap<>();
	private ArrayList<Reservation> reservationArrayList = new ArrayList<>();


	public HandleDataFromDB() {
	}

	public void populateLists() {
		clearLists();
		getEmployeesFromDatabaseToHashMap();
		getReservationsFromDatabaseToArrayList();
	}

	public void clearLists() {
		employeeHashMap.clear();
		reservationArrayList.clear();
	}



	private void getEmployeesFromDatabaseToHashMap() {
		ResultSet resultSet = DatabaseConnection.query(SELECT_ALL_FROM_TABLE + "employees");
		try {
			while (resultSet.next()) {
				Employee employee = new Employee();

				employee.setId(resultSet.getInt("id"));
				employee.setRealname(resultSet.getString("realname"));

				employeeHashMap.put(employee.getId(), employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void getReservationsFromDatabaseToArrayList() {
		ResultSet resultSet = DatabaseConnection.query(SELECT_ALL_FROM_TABLE + "reservations");
		try {
			while (resultSet.next()) {
				Reservation reservation = new Reservation();

				reservation.setId(resultSet.getInt("id"));
				reservation.setStart(resultSet.getString("start"));
				reservation.setEnd(resultSet.getString("end"));
				reservation.setCustomDiscount(resultSet.getInt("customDiscount"));
				reservation.setPeopleAmount(resultSet.getInt("peopleAmount"));
				reservation.setFk_customer_id(resultSet.getInt("fk_customer_id"));
				reservation.setFk_activity_id(resultSet.getInt("fk_activity_id"));
				reservation.setFk_user_id(resultSet.getInt("fk_user_id"));
				reservation.setFk_employee_id(resultSet.getInt("fk_employee_id"));

				reservationArrayList.add(reservation);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public HashMap<Integer, Employee> getEmployeeHashMap() {
		return employeeHashMap;
	}

	public ArrayList<Reservation> getReservationArrayList() {
		return reservationArrayList;
	}
}
