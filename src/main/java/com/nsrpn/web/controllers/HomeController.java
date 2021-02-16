package com.nsrpn.web.controllers;

import com.nsrpn.app.utils.Consts;
import com.nsrpn.app.utils.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/")
public class HomeController {

  @GetMapping
  public String home(Model model, HttpSession session) {
    if ((session.isNew()) || (Utils.getUserIdFromSession(session) == null))
      return "redirect:/login";
    else
      return "redirect:/books/shelf";
  }
}