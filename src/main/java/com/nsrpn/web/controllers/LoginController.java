package com.nsrpn.web.controllers;

import com.nsrpn.app.services.LoginService;
import com.nsrpn.web.forms.LoginEdit;
import com.nsrpn.web.forms.LoginForm;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

  private final Logger logger = Logger.getLogger(LoginController.class);
  private final LoginService loginService;

  @Autowired
  public LoginController(LoginService loginService) {
    this.loginService = loginService;
  }

  @GetMapping
  public String login(Model model) {
    String form;
    if (model.containsAttribute("register")) {
      model.addAttribute("isNew", "1");
      model.addAttribute("loginEdit", new LoginEdit());
      form = "loginEdit";
    } else {
      model.addAttribute("loginForm", new LoginForm());
      form = "login";
    }
    logger.info("GET returns " + form);
    return form;
  }

  @PostMapping("/auth")
  public String authenticate(LoginForm loginFrom) {
    if (loginService.authenticate(loginFrom)) {
      logger.info("login OK redirect to book shelf");
      return "redirect:/books/shelf";
    } else {
      logger.info("login FAIL redirect back to login");
      return "redirect:/login";
    }
  }

  @PostMapping("/register")
  public String register(LoginEdit loginEdit) {
    if (loginService.register(loginEdit))
      logger.info("New User was added");
    return "redirect:/login";
  }
}
