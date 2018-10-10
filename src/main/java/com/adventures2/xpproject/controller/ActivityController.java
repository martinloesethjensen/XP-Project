package com.adventures2.xpproject.controller;

import com.adventures2.xpproject.base.Acitivity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.*;
import java.security.*;
import java.util.ArrayList;
import javax.servlet.*;

import static javax.xml.ws.soap.AddressingFeature.ID;

@Controller
public class ActivityController {

    public static ArrayList<Acitivity> actiList = new ArrayList<>();




    @GetMapping("/ReserveAKT")
    public String orderActivity(Model model) {
      model.addAttribute("aktivitet",new Acitivity());


      return "ReserveAKT";

           }

           @PostMapping("/ReserveAKT")
    public String orderActivity(Acitivity  acitivity){

    return "redirect:/VisAktivitet";
    }




    }