package com.nsrpn.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class User extends BaseEntity {
  @Column
  private String userName;

  @Column
  private String pwd;

  public User(Long id, String title, String userName, String pwd) {
    super(id, title);
    this.userName = userName;
    this.pwd = pwd;
  }

  public User() {
    super();
  }

  public String getUserName() {
    return userName;
  }

  public String getPwd() {
    return pwd;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
  }

}
