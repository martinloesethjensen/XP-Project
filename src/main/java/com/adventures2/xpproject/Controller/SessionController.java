package com.adventures2.xpproject.Controller;

import com.adventures2.xpproject.DatabaseConnection;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SessionController {

    @GetMapping("/")
    public String view() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        return "/index";
    }

    @GetMapping("/homepage")
    public String viewHomepage() {

        return "/homepage";
    }
    @GetMapping("/login")
    public String viewLogin() {

        return "/login";
    }

    @PostMapping("/login")
    public String login() {

        return "/login";
    }


}
