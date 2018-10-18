package com.adventures2.xpproject.controller;

import com.adventures2.xpproject.base.DefectTools;
import com.adventures2.xpproject.logic.DefectLogic;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DefectController {


@GetMapping("/Report_defects")
public String reportDefects(Model model) {
    model.addAttribute("defects",new DefectTools());
    return "Report_defects";
    }
@PostMapping("/Report_defects")
 public String reportDefects(DefectTools defects) {
    System.out.println(defects);
    DefectLogic.writeDefectToDatabase(defects);
    return "redirect:/Report_defects";
 }
@GetMapping("/show_defects")
 public String showDefects(Model model){
model.addAttribute("DefectArray",DefectLogic.ReadDefects());
    return "show_defects";
    }

@PostMapping("delete_defects{ID}")
    public String deledefects(@PathVariable int ID){

    return "redirect:/show_defects";
    }
}
