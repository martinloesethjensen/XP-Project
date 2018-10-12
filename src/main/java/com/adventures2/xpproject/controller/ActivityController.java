package com.adventures2.xpproject.controller;

import com.adventures2.xpproject.base.Activity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class ActivityController {

    public static ArrayList<Activity> actiList = new ArrayList<>();




    @GetMapping("/ReserveAKT")
    public String orderActivity(Model model) {
      model.addAttribute("aktivitet",new Activity());


      return "ReserveAKT";

           }

           @PostMapping("/ReserveAKT")
    public String orderActivity(Activity activity){

    return "redirect:/VisAktivitet";
    }




    }