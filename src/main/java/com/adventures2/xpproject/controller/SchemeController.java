package com.adventures2.xpproject.controller;

import com.adventures2.xpproject.auth.Authenticate;
import com.adventures2.xpproject.logic.EmployeeLogic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class SchemeController {

  @GetMapping("/scheme/")
  public String overview(HttpSession session, Model model) {
    if(!Authenticate.isLoggedIn(session)) return "redirect:/";
    model.addAttribute("IS_LOGGED_IN", Authenticate.isLoggedIn(session));
    model.addAttribute("NIVEAU", session.getAttribute("NIVEAU"));
    model.addAttribute("REALNAME", session.getAttribute("REALNAME"));
    model.addAttribute("employees", EmployeeLogic.getEmployeesForScheme());
    return "/scheme/index";
  }
}
