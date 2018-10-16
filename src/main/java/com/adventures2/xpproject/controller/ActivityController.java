package com.adventures2.xpproject.controller;

import com.adventures2.xpproject.auth.Authenticate;
import com.adventures2.xpproject.base.Activity;
import com.adventures2.xpproject.logic.ActivityLogic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

@Controller
public class ActivityController {
  public static ArrayList<Activity> actiList = new ArrayList<>();
  String succesMessage = "";

  @GetMapping("/create_activity")
  public String orderActivity(HttpSession session, Model model) {
    //if(Authenticate.isLoggedIn(session)) {
    model.addAttribute("IS_LOGGED_IN", Authenticate.isLoggedIn(session));
    model.addAttribute("NIVEAU", session.getAttribute("NIVEAU"));
    model.addAttribute("REALNAME", session.getAttribute("REALNAME"));
    model.addAttribute("aktivitet", new Activity());
    return "createActivity";
    //}
    //return "redirect:/";
  }

  @PostMapping("/create_activity")
  public String orderActivity(HttpSession session, Model model, Activity activity) {
    ActivityLogic.createActivity(activity);
    //if(Authenticate.isLoggedIn(session)) {
    model.addAttribute("IS_LOGGED_IN", Authenticate.isLoggedIn(session));
    model.addAttribute("NIVEAU", session.getAttribute("NIVEAU"));
    model.addAttribute("REALNAME", session.getAttribute("REALNAME"));
    return "redirect:/createActivity";
    //}
    //return "redirect:/";

  }

  @GetMapping("edit_activity")
  public String editActivity(HttpSession session, Model model) {
    //if(Authenticate.isLoggedIn(session)) {
    model.addAttribute("IS_LOGGED_IN", Authenticate.isLoggedIn(session));
    model.addAttribute("NIVEAU", session.getAttribute("NIVEAU"));
    model.addAttribute("REALNAME", session.getAttribute("REALNAME"));
    return "redigAkt";
    //}
    //return "redirect:/";

  }

  @PostMapping("/edit_activity")
  public String editActivity(HttpSession session, Model model, Activity activity) {
    //if(Authenticate.isLoggedIn(session)) {
    model.addAttribute("IS_LOGGED_IN", Authenticate.isLoggedIn(session));
    model.addAttribute("NIVEAU", session.getAttribute("NIVEAU"));
    model.addAttribute("REALNAME", session.getAttribute("REALNAME"));
    ActivityLogic.updateActivity(activity);
    return "redirect:/createActivity";
    //}
    //return "redirect:/";

  }

  @PostMapping("/delete_activity")
  public String deleteActivity(HttpSession session, Model model, Activity activity) {
    //if(Authenticate.isLoggedIn(session)) {
    model.addAttribute("IS_LOGGED_IN", Authenticate.isLoggedIn(session));
    model.addAttribute("NIVEAU", session.getAttribute("NIVEAU"));
    model.addAttribute("REALNAME", session.getAttribute("REALNAME"));
    ActivityLogic.deleteActivity(activity);
    succesMessage = "Aktiviten blev slettet";
    return "redirect:/aktivitetogoversigt";
    //}
    //return "redirect:/";

  }

}