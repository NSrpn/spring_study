package com.nsrpn.app.entities;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
@Entity
@Table(name = "Users",
       indexes = {
        @Index(name = "idx_users_username",  columnList="username", unique = true)
       }
)
@NamedQueries(
  {
    @NamedQuery(name = "getUserByName", query = "SELECT u FROM User u WHERE u.userName = :p")}
)
public class User extends BaseEntity {

  @Column(unique=true, nullable = false)
  private String userName;

  @Column(nullable = false)
  private String pwd;

  @Column
  private String role;

  @Column
  private Boolean needPwdChange;

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

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public Boolean getNeedPwdChange() {
    return needPwdChange;
  }

  public void setNeedPwdChange(Boolean needPwdChange) {
    this.needPwdChange = needPwdChange;
  }
}
