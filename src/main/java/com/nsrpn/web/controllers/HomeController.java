package com.nsrpn.web.controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

  private Logger logger = Logger.getLogger(HomeController.class);

  @RequestMapping(value="/studymvc", method = RequestMethod.GET)
  public ModelAndView home() {
    logger.info("we are here");
    return new ModelAndView("index");
  }

}
