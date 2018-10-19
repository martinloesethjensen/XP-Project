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
    //if(Authenticate.isLoggedIn(session)) {
    model.addAttribute("IS_LOGGED_IN", Authenticate.isLoggedIn(session));
    model.addAttribute("NIVEAU", session.getAttribute("NIVEAU"));
    model.addAttribute("REALNAME", session.getAttribute("REALNAME"));
    model.addAttribute("aktivitet", new Activity());
    model.addAttribute("rules", new Limit());
    return "/activity/create_activity";
    //}
    //return "redirect:/";
  }

    @PostMapping("/activity/create")
    public String orderActivity(HttpSession session, Model model, Activity activity) {
      System.out.println(activity);
        ActivityLogic.createActivity(activity);

        //if(Authenticate.isLoggedIn(session)) {
        model.addAttribute("IS_LOGGED_IN", Authenticate.isLoggedIn(session));
        model.addAttribute("NIVEAU", session.getAttribute("NIVEAU"));
        model.addAttribute("REALNAME", session.getAttribute("REALNAME"));
        return "redirect:/activity/";
        //}
        //return "redirect:/";

    }

    @GetMapping("/activity/edit/{id}")
    public String editActivity(HttpSession session, Model model, @PathVariable("id") int id) {
        //if(Authenticate.isLoggedIn(session)) {
        model.addAttribute("activity", ActivityLogic.getActivityById(id));
        model.addAttribute("IS_LOGGED_IN", Authenticate.isLoggedIn(session));
        model.addAttribute("NIVEAU", session.getAttribute("NIVEAU"));
        model.addAttribute("REALNAME", session.getAttribute("REALNAME"));
        model.addAttribute("chosenActivity", id);
        return "/activity/edit_activity";
        //}
        //return "redirect:/";

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
        //}
        //return "redirect:/";

    }

    @GetMapping("/activity/delete/{id}")
    public String deleteAct(HttpSession session, Model model, Activity activity, @PathVariable("id") int id){
        //if(Authenticate.isLoggedIn(session)) {
        model.addAttribute("IS_LOGGED_IN", Authenticate.isLoggedIn(session));
        model.addAttribute("NIVEAU", session.getAttribute("NIVEAU"));
        model.addAttribute("REALNAME", session.getAttribute("REALNAME"));

        ActivityLogic.deleteActivity(id);

        succesMessage = "Aktiviteten blev slettet";
        return "redirect:/activity/";
        //}
        //return "redirect:/";
    }

    @GetMapping("/activity/")
    public String aktiviteterOversigt(Model model){
        model.addAttribute("activities_HashMap", ActivityLogic.getActivitiesFromDatabaseToHashMap());
        return "/activity/index";


    }
}