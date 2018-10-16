package com.adventures2.xpproject.controller;

import com.adventures2.xpproject.base.Activity;
import com.adventures2.xpproject.logic.ActivityLogic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class ActivityController {
    public static ArrayList<Activity> actiList = new ArrayList<>();
    String succesMessage = "";

    @GetMapping("/create_activity")
    public String orderActivity(Model model) {
        model.addAttribute("aktivitet", new Activity());
        return "createActivity";
    }

    @PostMapping("/create_activity")
    public String orderActivity(Activity activity) {
      ActivityLogic.createActivity(activity);
        return "redirect:/createActivity";
    }

    @GetMapping("edit_activity")
  public String editActivity(){
      return "redigAkt";
    }
  @PostMapping("/edit_activity")
  public String editActivity(Activity activity) {
    ActivityLogic.updateActivity(activity);
    return "redirect:/createActivity";
  }

  @PostMapping("/delete_activity")
  public String deleteActivity(Activity activity){
      ActivityLogic.deleteActivity(activity);
      succesMessage = "Aktiviten blev slettet";
      return "redirect:/aktivitetogoversigt";
  }

}