package com.adventures2.xpproject.controller;

import com.adventures2.xpproject.auth.Authenticate;
import com.adventures2.xpproject.base.Activity;
import com.adventures2.xpproject.base.Customer;
import com.adventures2.xpproject.base.Employee;
import com.adventures2.xpproject.base.Reservation;
import com.adventures2.xpproject.logic.ReservationLogic;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class ReservationController {
    private String successMessage = "";

    HashMap<Integer,Customer> hashMapCustomer = new HashMap<>();
    HashMap<Integer, Activity> hashMapActivity = new HashMap<>();
    HashMap<Integer, Employee> hashMapEmployee = new HashMap<>();
    ArrayList<Reservation> reservations = new ArrayList<>();
    public void populate() {



        Customer c1 = new Customer(1, "Bent", "28712544", "alsk@email.dk", false, "apple");
        Customer c2 = new Customer(6, "Bent", "28712544", "alsk@email.dk", false, "apple");
        Customer c3 = new Customer(3, "Bent", "28712544", "alsk@email.dk", false, "apple");
        Customer c4 = new Customer(4, "Bent", "28712544", "alsk@email.dk", false, "apple");

        Reservation r1 = new Reservation(1, "Mandag 12:00", "mandag 13:00", 1, 3, 1, 1, 1);
        Reservation r2 = new Reservation(2, "Mandag 12:00", "Mandag 13:00", 1, 4, 6, 2, 1);
        Reservation r3 = new Reservation(3, "Mandag 14:00", "Mandag 15:00", 1, 5, 3, 1, 1);
        Reservation r4 = new Reservation(4, "Mandag 15:00", "Mandag 16:00", 1, 6, 4, 2, 1);

        Activity activity1 = new Activity(1, "Go-kart", 10, 2);
        Activity activity2 = new Activity(2, "Paint-ball", 10, 2);

        Employee employee = new Employee(1, "Martin");

        reservations.add(r1);
        reservations.add(r2);
        reservations.add(r3);
        reservations.add(r4);
        hashMapCustomer.put(1, c1);
        hashMapCustomer.put(6, c2);
        hashMapCustomer.put(3, c3);
        hashMapCustomer.put(4, c4);
        hashMapActivity.put(1, activity1);
        hashMapActivity.put(2,activity2);
        hashMapEmployee.put(1, employee);

        System.out.println(hashMapActivity.get(reservations.get(1).getFk_activity_id()).getName());

    }

    @GetMapping("/")
    public String view(HttpSession session, Model model) {
        populate();
        model.addAttribute("IS_LOGGED_IN", Authenticate.isLoggedIn(session));
        model.addAttribute("NIVEAU", session.getAttribute("NIVEAU"));
        model.addAttribute("REALNAME", session.getAttribute("REALNAME"));
        model.addAttribute("dataMapCustomer", hashMapCustomer);
        model.addAttribute("dataList", reservations);
        model.addAttribute("dataMapAct", hashMapActivity);
        model.addAttribute("dataMapEmp", hashMapEmployee);

        System.out.println(reservations);
        return "/index";
    }



    @GetMapping("/reservation/create")
    public String create(HttpSession session) {
        if(Authenticate.isLoggedIn(session)) {
            //return "/reservation/create";
        }
        return "redirect:/";
    }

    @PostMapping("/reservation/create")
    public String create(HttpSession session, Reservation reservation, Customer customer, Employee employee) {
        ReservationLogic.create(reservation, customer, employee, session);
        return "redirect:/reservation/create";
    }

}
