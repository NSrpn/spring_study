package com.nsrpn.app.utils;

import com.nsrpn.app.entities.Book;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class Utils {
  public static boolean compareFieldValueWithFilter(String fltVal, String fldVal) {
    return (fltVal.isEmpty() || fldVal.contains(fltVal));
  }

  public static String getUserNameFromSession() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null) {
      return ((User)auth.getPrincipal()).getUsername();
    }
    return null;
  }

  public static boolean checkCurrentUserAdmin() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null) {
      if (auth.getPrincipal() instanceof User) {
        User user = (User) auth.getPrincipal();
        return user.getUsername().equals("admin") || user.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
      }
    }
    return false;
  }

  public static  boolean checkSession() {
    return (getUserNameFromSession() == null);
  }

  public static void setFilterToSession(String pagePrefix, HttpServletRequest rq) {
    Map<String, String> filterList = Book.getFilter(pagePrefix, rq);
    rq.getSession().setAttribute(Consts.Web.filterSessionName, filterList);
  }
}
