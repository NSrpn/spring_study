package com.nsrpn.web.forms;

public class LoginEdit {

  private String title;
  private String username;
  private String password;

  public LoginEdit(String title, String username, String password) {
    this.title = title;
    this.username = username;
    this.password = password;
  }

  public LoginEdit() {
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public String toString() {
    return "LoginEdit{" +
        "username='" + username + '\'' +
        ", password='" + password + '\'' +
        '}';
  }
}
