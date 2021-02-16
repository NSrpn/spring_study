package com.nsrpn.web.controllers;

import com.nsrpn.app.services.LoginService;
import com.nsrpn.app.utils.Consts;
import com.nsrpn.web.forms.LoginEdit;
import com.nsrpn.web.forms.LoginForm;
import com.nsrpn.web.validators.LoginValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value="/login")
public class LoginController {

  private final Logger logger = Logger.getLogger(LoginController.class);
  private final LoginService loginService;

  @Autowired
  LoginValidator loginValidator;

  @Autowired
  public LoginController(LoginService loginService) {
    this.loginService = loginService;
  }

  @InitBinder("loginEdit")
  protected void initLoginEditBinder(WebDataBinder binder) {
    binder.addValidators(loginValidator);
  }

  @InitBinder("loginForm")
  protected void initLoginFormBinder(WebDataBinder binder) {
    binder.addValidators(loginValidator);
  }

  @GetMapping
  public String login(Model model) {
    model.addAttribute("loginForm", new LoginForm());
    logger.info("GET to login");
    return "login";
  }

  @GetMapping("/register")
  public String register(Model model) {
    model.addAttribute("isNew", "1");
    model.addAttribute("loginEdit", new LoginEdit());
    logger.info("GET to loginEdit");
    return "loginEdit";
  }

  @PostMapping("/auth")
  public String authenticate(@Validated LoginForm loginFrom, BindingResult bindingResult, HttpServletRequest request) {
    Long id = loginService.authenticate(loginFrom);
    if (bindingResult.hasErrors()) {
      return "login";
    } else {
      if (id != null) {
        request.getSession().setAttribute(Consts.Web.userSessionName, id);
        logger.info("login OK redirect to book shelf");
        return "redirect:/shelf";
      } else {
        bindingResult.addError(new ObjectError("global", "Invalid name/password"));
        logger.info("login FAIL redirect back to login");
        return "login";
      }
    }
  }

  @PostMapping("/register")
  public String register(@Validated LoginEdit loginEdit, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "loginEdit";
    } else {
      if (loginService.register(loginEdit) )
        logger.info("New User was added");
    }
    return "redirect:/login";
  }
}
