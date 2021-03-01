package com.nsrpn.web.forms;

import com.nsrpn.app.entities.User;
import com.nsrpn.app.storage.UserStorage;

public class LoginEdit extends LoginForm {

  private String title;
  private String role;
  private Boolean admin;
  private Boolean needChangePwd;

  public LoginEdit() {
  }

  public LoginEdit(String userName) {
    if (userName.isEmpty()) {
      setTitle("");
      setUsername("");
      setAdmin(false);
      setNeedChangePwd(false);
    } else {
      User u = UserStorage.getInstance().getByUserName(userName);
      if (u != null) {
        setTitle(u.getTitle());
        setUsername(u.getUserName());
        setAdmin(u.getRole().equals("ADMIN"));
        setNeedChangePwd(u.getNeedPwdChange());
      }
    }
    setPassword("");
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public Boolean getNeedChangePwd() {
    return needChangePwd;
  }

  public void setNeedChangePwd(Boolean needChangePwd) {
    this.needChangePwd = needChangePwd;
  }

  public Boolean getAdmin() {
    return admin;
  }

  public void setAdmin(Boolean admin) {
    this.admin = admin;
  }
}
