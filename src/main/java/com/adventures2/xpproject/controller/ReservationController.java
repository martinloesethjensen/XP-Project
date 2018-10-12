package com.adventures2.xpproject.controller;

import com.adventures2.xpproject.auth.Authenticate;
import com.adventures2.xpproject.base.Customer;
import com.adventures2.xpproject.base.Reservation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ReservationController {
    private String successMessage = "";

    @PostMapping("/reservation/create")
    public String create(HttpSession session, Reservation reservation, Customer customer) {
        if(Authenticate.isLoggedIn(session)) {

        }
        return "redirect:/reservation/create";
    }
}
