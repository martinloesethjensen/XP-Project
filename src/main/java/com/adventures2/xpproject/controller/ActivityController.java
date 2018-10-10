package com.adventures2.xpproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ActivityController {

    @GetMapping("/activity_list")
    public String viewActivities(){
        return ("/activity_list");
    }
}
