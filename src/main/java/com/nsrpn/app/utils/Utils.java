package com.nsrpn.app.utils;

import com.nsrpn.app.entities.Book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class Utils {
  public static boolean compareFieldValueWithFilter(String fltVal, String fldVal) {
    return (fltVal.isEmpty() || fldVal.contains(fltVal));
  }

  public static Long getUserIdFromSession(HttpSession session) {
    return (Long)session.getAttribute(Consts.Web.userSessionName);
  }

  public static boolean checkSession(HttpServletRequest rq) {
    return checkSession(rq.getSession());
  }

  public static  boolean checkSession(HttpSession s) {
    return (getUserIdFromSession(s) == null);
  }

  public static void setFilterToSession(String pagePrefix, HttpServletRequest rq) {
    Map<String, String> filterList = Book.getFilter(pagePrefix, rq);
    rq.getSession().setAttribute(Consts.Web.filterSessionName, filterList);
  }
}
