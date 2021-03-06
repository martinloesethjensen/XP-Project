package com.adventures2.xpproject.controller;

import com.adventures2.xpproject.auth.Authenticate;
import com.adventures2.xpproject.base.Customer;
import com.adventures2.xpproject.base.Reservation;
import com.adventures2.xpproject.logic.ActivityLogic;
import com.adventures2.xpproject.logic.CustomerLogic;
import com.adventures2.xpproject.logic.EmployeeLogic;
import com.adventures2.xpproject.logic.ReservationLogic;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ReservationController {
  private String successMessage = "";
  private int activity = 0;
  private String date = "";

  private Reservation reservation;
  private Customer customer = new Customer();

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
    if (!Authenticate.isLoggedIn(session)) return "redirect:/";
    model.addAttribute("IS_LOGGED_IN", Authenticate.isLoggedIn(session));
    model.addAttribute("NIVEAU", session.getAttribute("NIVEAU"));
    model.addAttribute("REALNAME", session.getAttribute("REALNAME"));
    model.addAttribute("activities", ActivityLogic.getActivities());
    model.addAttribute("totalActivities", ActivityLogic.getTotalActivities() - 1);
    return "/reservation/create_step_1";

  }

  @GetMapping("/reservation/create/{id}")
  public String createStepTwo(HttpSession session, Model model, @PathVariable("id") int id) {
    if (!Authenticate.isLoggedIn(session)) return "redirect:/";
    model.addAttribute("IS_LOGGED_IN", Authenticate.isLoggedIn(session));
    model.addAttribute("NIVEAU", session.getAttribute("NIVEAU"));
    model.addAttribute("REALNAME", session.getAttribute("REALNAME"));
    model.addAttribute("activities", ActivityLogic.getActivities());
    model.addAttribute("totalActivities", ActivityLogic.getTotalActivities() - 1);
    model.addAttribute("chosenActivity", id);
    model.addAttribute("reservation", new Reservation());
    return "/reservation/create_step_2";

  }

    @PostMapping("/reservation/create/{id}")
    public String create(HttpSession session, Reservation reservation,
                         @RequestParam("action") String action,
                         @RequestParam("date") String date,
                         @RequestParam("activity") int activity_id,
                         @RequestParam("start") String startTime,
                         @RequestParam("end") String endTime)
    {

    if (action.equals("Tilbage")) {
      return "redirect:/reservation/create";

    } else if (action.equals("Næste")) {

            if (!date.equals("")) {
                try {
	                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	                Date date_temp_start = dateFormat.parse(date + " " + startTime.substring(0,5));
	                Date date_temp_end = dateFormat.parse(date + " " + endTime.substring(0,5));
	                long unixTimeStart = date_temp_start.getTime() / 1000 + (3600 * 2);
	                long unixTimeEnd = date_temp_end.getTime() / 1000 + (3600 * 2);

          //Populate reservation with time and activity_id
          reservation.setStart(String.valueOf(unixTimeStart));
          reservation.setEnd(String.valueOf(unixTimeEnd));
          reservation.setFk_activity_id(activity_id);
        } catch (ParseException e) {
          e.printStackTrace();
        }
      }

      this.reservation = reservation;

      return "redirect:/reservation/create_step_3";
    }
    return "redirect:/reservation/create";
  }

  @GetMapping("/reservation/create_step_3")
  public String createActivity3(HttpSession session, Model model) {
    if (!Authenticate.isLoggedIn(session)) return "redirect:/";
    model.addAttribute("IS_LOGGED_IN", Authenticate.isLoggedIn(session));
    model.addAttribute("NIVEAU", session.getAttribute("NIVEAU"));
    model.addAttribute("REALNAME", session.getAttribute("REALNAME"));
    model.addAttribute("employees_HashMap", EmployeeLogic.getEmployeesFromDatabaseToHashMap());
    model.addAttribute("customer", customer);
    model.addAttribute("reservation", reservation);

    return "reservation/create_step_3";
  }

  @PostMapping("/reservation/create_step_3")
  public String createActivity3(HttpSession session, Customer customer,
                                @RequestParam("action") String action,
                                @RequestParam("employeeAssigned") int employee_id) {
    if (action.equals("Tilbage")) {
      return "redirect:/reservation/create";
    }

    //populate reservation object employee id
    this.reservation.setFk_employee_id(employee_id);

    ReservationLogic.create(this.reservation, customer, session);

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
    if (!Authenticate.isLoggedIn(session)) return "redirect:/";
    model.addAttribute("reservation", ReservationLogic.getReservationById(id));
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
    //populating reservation object with id's for customer, employee, and activity
    reservation.setFk_customer_id(customer_id);
    reservation.setFk_activity_id(activity_id);
    reservation.setFk_employee_id(employee_id);

    ReservationLogic.updateReservation(reservation, reservation_id);
    return "redirect:/";
  }

  @GetMapping("reservation/delete/{id}")
  public String deleteReservation(HttpSession session, Model model, @PathVariable("id") int id) {
    if (!Authenticate.isLoggedIn(session)) return "redirect:/";
    ReservationLogic.deleteReservation(id);
    return "redirect:/";
  }
}
