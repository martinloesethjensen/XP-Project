package com.adventures2.xpproject.controller;

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
import javax.xml.ws.http.HTTPBinding;

@Controller
public class ReservationController {
    private String successMessage = "";

    @GetMapping("/")
    public String view(HttpSession session, Model model) {
        model.addAttribute("IS_LOGGED_IN", Authenticate.isLoggedIn(session));
        model.addAttribute("NIVEAU", session.getAttribute("NIVEAU"));
        model.addAttribute("REALNAME", session.getAttribute("REALNAME"));
        model.addAttribute("customer_HashMap", CustomerLogic.getCustomersFromDatabaseToHashMap());
        //model.addAttribute("reservation_ArrayList", ReservationLogic.getReservationsFromDatabaseToArrayList());
        model.addAttribute("activities_HashMap", ActivityLogic.getActivitiesFromDatabaseToHashMap());
        model.addAttribute("employees_HashMap", EmployeeLogic.getEmployeesFromDatabaseToHashMap());

        return "/index";
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
        return "/reservation/create_step_2";
        //}
        //return "redirect:/";
    }

    @PostMapping("/reservation/create/{id}")
    public String create(HttpSession session, Reservation reservation, Customer customer, Employee employee, @RequestParam("action") String action) {
        //ReservationLogic.create(reservation, customer, employee, session);
        if(action.equals("Tilbage")) {
            return "redirect: /reservation/create";
        } else if(action.equals("Næste")) {

        }
        return "redirect:/reservation/create";
    }

    @GetMapping("/chef/")
    public String chefPage(HttpSession session) {
        if(Authenticate.isLoggedIn(session) && Authenticate.isChef(session))
            return "/chef/index";
        else
            return "redirect:/";
    }


}
