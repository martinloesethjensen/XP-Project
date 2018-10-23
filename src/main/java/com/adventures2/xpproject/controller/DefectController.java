package com.adventures2.xpproject.controller;

import com.adventures2.xpproject.auth.Authenticate;
import com.adventures2.xpproject.base.DefectTools;
import com.adventures2.xpproject.logic.DefectLogic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class DefectController {


  @GetMapping("/Report_defects")
  public String reportDefects(HttpSession session, Model model) {
    model.addAttribute("IS_LOGGED_IN", Authenticate.isLoggedIn(session));
    model.addAttribute("NIVEAU", session.getAttribute("NIVEAU"));
    model.addAttribute("REALNAME", session.getAttribute("REALNAME"));
    model.addAttribute("defects", new DefectTools());
    return "defects/Report_defects";
  }

  @PostMapping("/Report_defects")
  public String reportDefects(DefectTools defects) {
    DefectLogic.writeDefectToDatabase(defects);
    return "redirect:/show_defects";
  }

  @GetMapping("/show_defects")
  public String showDefects( HttpSession session, Model model) {
    model.addAttribute("IS_LOGGED_IN", Authenticate.isLoggedIn(session));
    model.addAttribute("NIVEAU", session.getAttribute("NIVEAU"));
    model.addAttribute("REALNAME", session.getAttribute("REALNAME"));
    model.addAttribute("DefectArray", DefectLogic.ReadDefects());
    return "defects/show_defects";
  }

  @GetMapping("delete_defects/{ID}")
  public String deledefects(@PathVariable("ID") int ID, DefectLogic defects) {
    DefectLogic.deletedefects(ID);
    return "redirect:/show_defects";
  }
}
