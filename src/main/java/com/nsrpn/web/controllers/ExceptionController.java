package com.nsrpn.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ExceptionController {
  @GetMapping("/404")
  public String ex404(Model model, HttpSession session) {
    model.addAttribute("errorMessage", "Такой страницы не существует");
    model.addAttribute("backPage", "/");
    return "/exc/ex404";
  }
}
