package com.adventures2.xpproject.controller;

import com.adventures2.xpproject.auth.Authenticate;
import com.adventures2.xpproject.base.Activity;
import com.adventures2.xpproject.base.Limit;
import com.adventures2.xpproject.logic.ActivityLogic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;

@Controller
public class ActivityController {
  String succesMessage = "";

  @GetMapping("/activity/create")
  public String orderActivity(HttpSession session, Model model) {
    if (!Authenticate.isLoggedIn(session)) return "redirect:/";
    model.addAttribute("IS_LOGGED_IN", Authenticate.isLoggedIn(session));
    model.addAttribute("NIVEAU", session.getAttribute("NIVEAU"));
    model.addAttribute("REALNAME", session.getAttribute("REALNAME"));
    model.addAttribute("aktivitet", new Activity());
    model.addAttribute("rules", new Limit());
    return "/activity/create_activity";

  }

  @PostMapping("/activity/create")
  public String orderActivity(HttpSession session, Model model, Activity activity) {
    if (!Authenticate.isLoggedIn(session)) return "redirect:/";
    ActivityLogic.createActivity(activity);
    model.addAttribute("IS_LOGGED_IN", Authenticate.isLoggedIn(session));
    model.addAttribute("NIVEAU", session.getAttribute("NIVEAU"));
    model.addAttribute("REALNAME", session.getAttribute("REALNAME"));
    return "redirect:/activity/";
  }

  @GetMapping("/activity/edit/{id}")
  public String editActivity(HttpSession session, Model model, @PathVariable("id") int id) {
    if (!Authenticate.isLoggedIn(session)) return "redirect:/";
    model.addAttribute("activity", ActivityLogic.getActivityById(id));
    model.addAttribute("IS_LOGGED_IN", Authenticate.isLoggedIn(session));
    model.addAttribute("NIVEAU", session.getAttribute("NIVEAU"));
    model.addAttribute("REALNAME", session.getAttribute("REALNAME"));
    model.addAttribute("chosenActivity", id);
    return "/activity/edit_activity";
  }

  @PostMapping("/activity/edit/{id}")
  public String editActivity(HttpSession session, Model model, Activity activity, @PathVariable("id") int id) {
    System.out.println(activity);
    //if(Authenticate.isLoggedIn(session)) {
    model.addAttribute("IS_LOGGED_IN", Authenticate.isLoggedIn(session));
    model.addAttribute("NIVEAU", session.getAttribute("NIVEAU"));
    model.addAttribute("REALNAME", session.getAttribute("REALNAME"));
    ActivityLogic.updateActivity(activity);
    return "redirect:/activity/";
  }

  @GetMapping("/activity/delete/{id}")
  public String deleteAct(HttpSession session, Model model, Activity activity, @PathVariable("id") int id) {
    if (!Authenticate.isLoggedIn(session)) return "redirect:/";
    model.addAttribute("IS_LOGGED_IN", Authenticate.isLoggedIn(session));
    model.addAttribute("NIVEAU", session.getAttribute("NIVEAU"));
    model.addAttribute("REALNAME", session.getAttribute("REALNAME"));

    ActivityLogic.deleteActivity(id);

    succesMessage = "Aktiviteten blev slettet";
    return "redirect:/activity/";
  }

  @GetMapping("/activity/")
  public String aktiviteterOversigt(HttpSession session, Model model) {
    if (!Authenticate.isLoggedIn(session)) return "redirect:/";
    model.addAttribute("IS_LOGGED_IN", Authenticate.isLoggedIn(session));
    model.addAttribute("NIVEAU", session.getAttribute("NIVEAU"));
    model.addAttribute("REALNAME", session.getAttribute("REALNAME"));
    model.addAttribute("activities_HashMap", ActivityLogic.getActivitiesFromDatabaseToHashMap());
    return "/activity/index";
  }
}