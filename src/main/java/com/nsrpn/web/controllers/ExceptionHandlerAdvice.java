package com.nsrpn.web.controllers;

import com.nsrpn.app.exceptions.AuthException;
import com.nsrpn.app.exceptions.UserException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerAdvice {
  @ExceptionHandler(AuthException.class)
  public ModelAndView loginException(AuthException ex, HttpServletRequest request) {
    ModelAndView modelAndView = new ModelAndView("/exc/ex404");
    modelAndView.addObject("errorMessage", ex.getMessage());
    if (!ex.getMessages().isEmpty())
      modelAndView.addObject("errorList", ex.getMessages());
    modelAndView.addObject("backPage", request.getRequestURI());
    return modelAndView;
  }

  @ExceptionHandler(UserException.class)
  public ModelAndView userException(UserException ex, HttpServletRequest request) {
    ModelAndView modelAndView = new ModelAndView("/exc/ex404");
    modelAndView.addObject("errorMessage", ex.getMessage());
    modelAndView.addObject("backPage", request.getRequestURI());
    return modelAndView;
  }

}
