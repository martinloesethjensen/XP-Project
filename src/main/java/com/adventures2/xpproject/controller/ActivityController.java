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
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class ActivityController {
    //public static ArrayList<Activity> actiList = lillemarcusertræt();
    String succesMessage = "";

  @GetMapping("/create_activity")
  public String orderActivity(HttpSession session, Model model) {
    //if(Authenticate.isLoggedIn(session)) {
    model.addAttribute("IS_LOGGED_IN", Authenticate.isLoggedIn(session));
    model.addAttribute("NIVEAU", session.getAttribute("NIVEAU"));
    model.addAttribute("REALNAME", session.getAttribute("REALNAME"));
    model.addAttribute("aktivitet", new Activity());
    model.addAttribute("rules", new Limit());
    return "create_activity";
    //}
    //return "redirect:/";
  }

    @PostMapping("/create_activity")
    public String orderActivity(HttpSession session, Model model, Activity activity) {
      System.out.println(activity);
        ActivityLogic.createActivity(activity);

        //if(Authenticate.isLoggedIn(session)) {
        model.addAttribute("IS_LOGGED_IN", Authenticate.isLoggedIn(session));
        model.addAttribute("NIVEAU", session.getAttribute("NIVEAU"));
        model.addAttribute("REALNAME", session.getAttribute("REALNAME"));
        return "redirect:/create_activity";
        //}
        //return "redirect:/";

    }

    @GetMapping("edit_activity/{id}")
    public String editActivity(HttpSession session, Model model, @PathVariable int id) {
        //if(Authenticate.isLoggedIn(session)) {
        model.addAttribute("activity", ActivityLogic.getActivityById(id));
        model.addAttribute("IS_LOGGED_IN", Authenticate.isLoggedIn(session));
        model.addAttribute("NIVEAU", session.getAttribute("NIVEAU"));
        model.addAttribute("REALNAME", session.getAttribute("REALNAME"));
        return "edit_activity";
        //}
        //return "redirect:/";

    }

    @PostMapping("/edit_activity")
    public String editActivity(HttpSession session, Model model, Activity activity) {
      System.out.println(activity);
        //if(Authenticate.isLoggedIn(session)) {
        model.addAttribute("IS_LOGGED_IN", Authenticate.isLoggedIn(session));
        model.addAttribute("NIVEAU", session.getAttribute("NIVEAU"));
        model.addAttribute("REALNAME", session.getAttribute("REALNAME"));
        ActivityLogic.updateActivity(activity);
        return "redirect:/aktiviteterOversigt";
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
        return "redirect:/aktiviteterOversigt";
        //}
        ///return "redirect:/";

    }

//    @PostMapping("/ReserveAKT")
//    public String orderActivity(Activity activity) {
//        return "redirect:/VisAktivitet";
//    }
//
//
//    @GetMapping("/opretres")
//    public String createActivity(Model model) {
//        model.addAttribute("aktivitet", new Activity());
//        return "opretres";
//    }
//
//    @PostMapping("/opretres")
//    public String createActivity(Activity activity) {
//
//
//        return "redirect:/opretres";
//    }
//
//
//
    @GetMapping("/aktiviteterOversigt")
    public String aktiviteterOversigt(Model model){
        model.addAttribute("activities_HashMap", ActivityLogic.getActivitiesFromDatabaseToHashMap());
        return "aktiviteterOversigt";


    }

    public static ArrayList<Activity> lillemarcusertræt () {
       ArrayList<Activity> hallo= new ArrayList<>();
       Activity h= new Activity("kjsdf",20,"20",20,"ldnsflk",20);
       hallo.add(h);
       return hallo;

    }
}