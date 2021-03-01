package com.nsrpn.web.controllers;

import com.nsrpn.app.utils.Utils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

//@Controller
//@RequestMapping(value="/")
public class HomeController {

  @GetMapping
  public String home(Model model, HttpSession session) {
    if ((session.isNew()) || (Utils.getUserNameFromSession() == null))
      return "redirect:/login";
    else
      return "redirect:/shelf";
  }
}