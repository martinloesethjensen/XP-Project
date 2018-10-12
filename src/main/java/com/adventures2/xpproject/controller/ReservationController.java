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

@Controller
public class ReservationController {
    private String successMessage = "";

    @GetMapping("/")
    public String view(HttpSession session, Model model) {
        model.addAttribute("IS_LOGGED_IN", Authenticate.isLoggedIn(session));
        model.addAttribute("NIVEAU", session.getAttribute("NIVEAU"));
        model.addAttribute("REALNAME", session.getAttribute("REALNAME"));
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
