package com.adventures2.xpproject.controller;

import com.adventures2.xpproject.DatabaseConnection;
import com.adventures2.xpproject.auth.Authenticate;
import com.adventures2.xpproject.base.Customer;
import com.adventures2.xpproject.base.Employee;
import com.adventures2.xpproject.base.Reservation;
import com.adventures2.xpproject.logic.ActivityLogic;
import com.adventures2.xpproject.logic.CustomerLogic;
import com.adventures2.xpproject.logic.EmployeeLogic;
import com.adventures2.xpproject.logic.ReservationLogic;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.AttributedString;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ReservationController {
    private String successMessage = "";
    private int activity = 0;
    private String date = "";
    //kan ikke
    // bruges
    private Reservation reservation;
    private Customer customer;
    private Employee employee;

    @GetMapping("/")
    public String view(HttpSession session, Model model) {
        model.addAttribute("IS_LOGGED_IN", Authenticate.isLoggedIn(session));
        model.addAttribute("NIVEAU", session.getAttribute("NIVEAU"));
        model.addAttribute("REALNAME", session.getAttribute("REALNAME"));
        model.addAttribute("customer_HashMap", CustomerLogic.getCustomersFromDatabaseToHashMap());
        model.addAttribute("reservation_ArrayList", ReservationLogic.getReservationsFromDatabaseToArrayList(activity, date));
        model.addAttribute("activities_HashMap", ActivityLogic.getActivitiesFromDatabaseToHashMap());
        model.addAttribute("employees_HashMap", EmployeeLogic.getEmployeesFromDatabaseToHashMap());

        return "/index";
    }

    @PostMapping("/")
    public String view(HttpSession session,
                       @RequestParam("search") String search,
                       @RequestParam("activity") int actitvity,
                       @RequestParam("date") String date) {
        if (search.equals("Søg")) {
            this.activity = actitvity;
            this.date = date;
        }

        return "redirect:/";
    }

    @GetMapping("/reservation/create")
    public String createStepOne(HttpSession session, Model model) {
        //if(Authenticate.isLoggedIn(session)) {
        model.addAttribute("IS_LOGGED_IN", Authenticate.isLoggedIn(session));
        model.addAttribute("NIVEAU", session.getAttribute("NIVEAU"));
        model.addAttribute("REALNAME", session.getAttribute("REALNAME"));
        model.addAttribute("activities", ActivityLogic.getActivities());
        model.addAttribute("totalActivities", ActivityLogic.getTotalActivities() - 1);
        return "/reservation/create_step_1";
        //}
        //return "redirect:/";
    }

    @GetMapping("/reservation/create/{id}")
    public String createStepTwo(HttpSession session, Model model, @PathVariable("id") int id) {
        //if(Authenticate.isLoggedIn(session)) {
        model.addAttribute("IS_LOGGED_IN", Authenticate.isLoggedIn(session));
        model.addAttribute("NIVEAU", session.getAttribute("NIVEAU"));
        model.addAttribute("REALNAME", session.getAttribute("REALNAME"));
        model.addAttribute("activities", ActivityLogic.getActivities());
        model.addAttribute("totalActivities", ActivityLogic.getTotalActivities() - 1);
        model.addAttribute("chosenActivity", id);
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("customer", new Customer());
        model.addAttribute("employee", new Employee());
        return "/reservation/create_step_2";
        //}
        //return "redirect:/";
    }

    @PostMapping("/reservation/create/{id}")
    public String create(HttpSession session, Reservation reservation, Customer customer, Employee employee,
                         @RequestParam("action") String action,
                         @RequestParam("date") String date,
                         @RequestParam("activity") int activity_id) {

        if (action.equals("Tilbage")) {
            return "redirect:/reservation/create";

        } else if (action.equals("Næste")) {

            if (!date.equals("")) {
                try {
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                    Date date_temp = dateFormat.parse(date);

                    long unixTimeStart = date_temp.getTime() / 1000;
                    long unixTimeEnd = unixTimeStart + 86400;

                    reservation.setStart(String.valueOf(unixTimeStart));
                    reservation.setEnd(String.valueOf(unixTimeEnd));
                    reservation.setFk_activity_id(activity_id);
                } catch (ParseException e) {
                    e.printStackTrace();

                }
            }

            this.reservation = reservation;
            this.customer = customer;
            this.employee = employee;
            System.out.println(this.reservation);
            System.out.println(this.customer);
            System.out.println(this.employee);
            return "redirect:/reservation/create_step_3";
        }
        return "redirect:/reservation/create";
    }

    @GetMapping("/reservation/create_step_3")
    public String createActivity3(HttpSession session, Model model) {
        model.addAttribute("IS_LOGGED_IN", Authenticate.isLoggedIn(session));
        model.addAttribute("NIVEAU", session.getAttribute("NIVEAU"));
        model.addAttribute("REALNAME", session.getAttribute("REALNAME"));
        model.addAttribute("employees_HashMap", EmployeeLogic.getEmployeesFromDatabaseToHashMap());
        model.addAttribute("employee", employee);
        model.addAttribute("customer", customer);
        model.addAttribute("reservation", reservation);
        System.out.println(this.reservation);
        System.out.println(this.customer);
        System.out.println(this.employee);

        return "reservation/create_step_3";
    }

    @PostMapping("/reservation/create_step_3")
    public String createActivity3(Reservation reservation, Customer customer, Employee employee,
                                  @RequestParam("action") String action,
                                  @RequestParam("employeeAssigned") int employee_id) {
        if (action.equals("Tilbage")) {
            return "redirect:/reservation/create";
        }
        System.out.println(employee_id);
        this.employee.setId(employee_id);
	    this.employee.setRealname(EmployeeLogic.getEmployeesFromDatabaseToHashMap().get(employee_id).getRealname());
	    this.reservation.setFk_employee_id(employee_id);
	    System.out.println(this.employee.getRealname());


		//this.customer = customer;

	    try {
		    PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(
		    		"INSERT INTO customers (company, name, phone, email, newsmail) VALUES (?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
		    preparedStatement.setString(1, customer.getCompanyName());
		    preparedStatement.setString(2, customer.getName());
		    preparedStatement.setString(3, customer.getTelephone());
		    preparedStatement.setString(4, customer.getEmail());
		    preparedStatement.setInt(5, customer.isNewsmail() ? 1 : 0);

		    int customer_id = DatabaseConnection.insertReturnId(preparedStatement);

		    //this.customer.setId(customer_id);
		    this.reservation.setFk_customer_id(customer_id);
	    } catch (SQLException e) {
		    e.printStackTrace();
	    }


        System.out.println(this.reservation);
        System.out.println(customer);
        System.out.println(employee);

        //ReservationLogic.create(reservation, customer, employee, session);



        return "redirect:/reservation/create";
    }

    @GetMapping("/chef/")
    public String chefPage(HttpSession session) {
        if (Authenticate.isLoggedIn(session) && Authenticate.isChef(session))
            return "/chef/index";
        else
            return "redirect:/";
    }

    @GetMapping("/editReservation/{id}")
    public String editReservation(HttpSession session, Model model, @PathVariable("id") int id) {
//        if (Authenticate.isLoggedIn(session)) {
        model.addAttribute("reservation", ReservationLogic.getReservationById(id));
//            return "/chef/index";
//        }

        System.out.println(ReservationLogic.getReservationById(id));


        model.addAttribute("IS_LOGGED_IN", Authenticate.isLoggedIn(session));
        model.addAttribute("NIVEAU", session.getAttribute("NIVEAU"));
        model.addAttribute("REALNAME", session.getAttribute("REALNAME"));
        model.addAttribute("customer_HashMap", CustomerLogic.getCustomersFromDatabaseToHashMap());
        model.addAttribute("activities_HashMap", ActivityLogic.getActivitiesFromDatabaseToHashMap());
        model.addAttribute("employees_HashMap", EmployeeLogic.getEmployeesFromDatabaseToHashMap());

        return "/editReservation";
    }

    @PostMapping("/editReservation")
    public String editReservation(Reservation reservation,
                                  @RequestParam("customer_id") int customer_id,
                                  @RequestParam("reservation_id") int reservation_id,
                                  @RequestParam("activity_id") int activity_id,
                                  @RequestParam("employee_id") int employee_id
                                  ) {

        reservation.setFk_customer_id(customer_id);
        reservation.setFk_activity_id(activity_id);
        reservation.setFk_employee_id(employee_id);
        System.out.println(reservation);
//        updateReservation(reservation, reservation_id);
        return "redirect:/";
    }


    public void updateReservation(Reservation reservation, int reservation_id) {
        DatabaseConnection databaseConnection = (DatabaseConnection) DatabaseConnection.getConnection();
        Connection connection = databaseConnection.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("UPDATE reservations INNER JOIN employees on id INNER JOIN customers on id SET reservations_start = ?, reservations_end =?, " +
                    "reservations_customDiscount = ?, reservations_peopleAmount = ?, reservations_fk_activity_id = ?, " +
                    "reservations.fk_customer_id = ?, reservations_fk_user_id = ?, reservations_k_employee_id = ?");
            preparedStatement.setString(1, reservation.getStart());
            preparedStatement.setString(2, reservation.getEnd());
            preparedStatement.setInt(3, reservation.getCustomDiscount());
            preparedStatement.setInt(4, reservation.getPeopleAmount());
            preparedStatement.setInt(5, reservation.getFk_activity_id());
            preparedStatement.setInt(6, reservation.getFk_customer_id());
            preparedStatement.setInt(7, reservation.getFk_user_id());
            preparedStatement.setInt(8, reservation_id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
