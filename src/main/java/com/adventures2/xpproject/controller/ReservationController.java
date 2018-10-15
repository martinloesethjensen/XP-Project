package com.adventures2.xpproject.controller;

import com.adventures2.xpproject.auth.Authenticate;
import com.adventures2.xpproject.base.Customer;
import com.adventures2.xpproject.base.Employee;
import com.adventures2.xpproject.base.Reservation;
import com.adventures2.xpproject.logic.ActivityLogic;
import com.adventures2.xpproject.logic.ReservationLogic;
import com.adventures2.xpproject.service.HandleDataFromDB;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ReservationController {
    private String successMessage = "";
    HandleDataFromDB handleDataFromDB = new HandleDataFromDB();


    public void populate() {

        handleDataFromDB.clearLists();

        handleDataFromDB.populateLists();

    }

    @GetMapping("/")
    public String view(HttpSession session, Model model) {

        // When going to index it should search for reservations on the date and get all from there.

        populate();
        model.addAttribute("IS_LOGGED_IN", Authenticate.isLoggedIn(session));
        model.addAttribute("NIVEAU", session.getAttribute("NIVEAU"));
        model.addAttribute("REALNAME", session.getAttribute("REALNAME"));
        model.addAttribute("customer_HashMap", handleDataFromDB.getCustomerHashMap());
        model.addAttribute("reservation_ArrayList", handleDataFromDB.getReservationArrayList());
        model.addAttribute("activities_HashMap", handleDataFromDB.getActivityHashMap());
        model.addAttribute("employees_HashMap", handleDataFromDB.getEmployeeHashMap());

        return "/index";
    }

    // Postmapping for search option. Search should be on all activities by default.
    // Ajax could be used when selecting activities? Find out with the other members regarding how the
    // search function should work.
    // Possibly only going to make a new ArrayList<Reservation> with the reservations that you conditioned for.
    // Then use a setter (setReservationArrayList()) to set it to the new list.
    // Then redirect back to /index.

    @GetMapping("/reservation/create")
    public String createStepOne(HttpSession session, Model model) {
        //if(Authenticate.isLoggedIn(session)) {
        model.addAttribute("activities", ActivityLogic.getActivities());
        model.addAttribute("totalActivities", ActivityLogic.getTotalActivities() - 1);
        return "/reservation/create_step_1";
        //}
        //return "redirect:/";
    }

    @GetMapping("/reservation/create/{id}")
    public String createStepTwo(HttpSession session, Model model) {
        //if(Authenticate.isLoggedIn(session)) {
        model.addAttribute("activities", ActivityLogic.getActivities());
        model.addAttribute("totalActivities", ActivityLogic.getTotalActivities() - 1);
        return "/reservation/create_step_2";
        //}
        //return "redirect:/";
    }

    @PostMapping("/reservation/create")
    public String create(HttpSession session, Reservation reservation, Customer customer, Employee employee) {
        ReservationLogic.create(reservation, customer, employee, session);
        return "redirect:/reservation/create";
    }

    @GetMapping("/landingpage_employer")
    public String landingpageEmployer() {
        return "/employer/landingpage_employer";
    }
}
