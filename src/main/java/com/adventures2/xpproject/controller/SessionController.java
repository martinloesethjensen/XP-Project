package com.adventures2.xpproject.controller;

import com.adventures2.xpproject.auth.Authenticate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
public class SessionController {

    @GetMapping("/")
    public String view(HttpSession session) {
        return "/index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        Authenticate.logout(session);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String viewLogin() {
        return "/login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("action") String action,
            HttpSession session) throws SQLException {
        if(action.equals("Log Ind"))
            Authenticate.login(username, password, session);
        return "redirect:/";
    }
}
