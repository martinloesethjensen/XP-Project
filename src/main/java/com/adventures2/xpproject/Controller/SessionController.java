package com.adventures2.xpproject.Controller;

import com.adventures2.xpproject.DatabaseConnection;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SessionController {

    @GetMapping("/")
    public String view() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        return "/index";
    }
}
