package com.nsrpn.web.forms;

import com.nsrpn.app.entities.User;
import com.nsrpn.app.storage.UserStorage;

public class LoginEdit extends LoginForm {

  private String title;

  public LoginEdit() {
  }

  public LoginEdit(String userName) {
    if (userName.isEmpty()) {
      setTitle("");
      setUsername("");
    } else {
      User u = UserStorage.getInstance().getByUserName(userName);
      if (u != null) {
        setTitle(u.getTitle());
        setUsername(u.getUserName());
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
}
