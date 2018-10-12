package com.adventures2.xpproject.controller;

import com.adventures2.xpproject.base.Activity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.*;
import java.security.*;
import java.util.ArrayList;
import javax.servlet.*;

import static javax.xml.ws.soap.AddressingFeature.ID;

/*@Controller
public class ActivityController {

    public static ArrayList<Activity> actiList = new ArrayList<>();




    @GetMapping("/ReserveAKT")
    public String orderActivity(@RequestParam(value ="ID", defaultValue = "-1") int activityID, Model model) {

        int activity = selectUser(activityID);

        for(int i = 0; i < actiList.size(); i++){
            if(selectUser(activityID)==null){
                activity.setID(activityID){
                }

                model.addAttribute("acitivy", activity);
            }


    }

    public static int selectUser(int activityID){
        int i = 2;

        return i;
    }

    public ActivityController() {

    }


}
*/