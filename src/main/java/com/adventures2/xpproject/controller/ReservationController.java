package com.adventures2.xpproject.controller;

import com.adventures2.xpproject.auth.Authenticate;
import com.adventures2.xpproject.base.Customer;
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
    ArrayList<Reservation> reservations = new ArrayList<>();
    public void populate() {



        Customer c1 = new Customer(1, "Bent", "28712544", "alsk@email.dk", false, "apple");
        Customer c2 = new Customer(6, "Bent", "28712544", "alsk@email.dk", false, "apple");
        Customer c3 = new Customer(3, "Bent", "28712544", "alsk@email.dk", false, "apple");
        Customer c4 = new Customer(4, "Bent", "28712544", "alsk@email.dk", false, "apple");

        Reservation r1 = new Reservation(1, "Mandag 12:00", "mandag 13:00", 1, 3, 1, 1, 1);
        Reservation r2 = new Reservation(2, "start", "end", 1, 4, 6, 1, 1);
        Reservation r3 = new Reservation(3, "start", "end", 1, 5, 3, 1, 1);
        Reservation r4 = new Reservation(4, "start", "end", 1, 6, 4, 1, 1);

        reservations.add(r1);
        reservations.add(r2);
        reservations.add(r3);
        reservations.add(r4);
        hashMapCustomer.put(1, c1);
        hashMapCustomer.put(6, c2);
        hashMapCustomer.put(3, c3);
        hashMapCustomer.put(4, c4);

    }

    @GetMapping("/")
    public String view(HttpSession session, Model model) {
        populate();
        model.addAttribute("IS_LOGGED_IN", Authenticate.isLoggedIn(session));
        model.addAttribute("NIVEAU", session.getAttribute("NIVEAU"));
        model.addAttribute("REALNAME", session.getAttribute("REALNAME"));
        model.addAttribute("dataMap", hashMapCustomer);
        model.addAttribute("dataList", reservations);

        System.out.println(reservations);
        return "/index";
    }



    @GetMapping("/reservation/create")
    public String create(HttpSession session) {
        //if(Authenticate.isLoggedIn(session)) {
            session.setAttribute("ID", 1);
            Customer customer = new Customer("Marco Bang Romeri", "28673631", "marco@romeri.dk", true, "");
            Reservation reservation = new Reservation("123", "123", 0, 10, 0, 2, 1);

            ReservationLogic.create(reservation, customer, session);
            return "/test";
        //}
        //return "redirect:/login";
    }

    @PostMapping("/reservation/create")
    public String create(HttpSession session, Reservation reservation, Customer customer) {

            ReservationLogic.create(reservation, customer, session);

        return "redirect:/reservation/create";
    }

}
