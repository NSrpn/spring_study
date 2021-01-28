package com.nsrpn.app.entities;

import com.nsrpn.app.utils.Consts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="UserBooks")
@NamedQuery(name = "UserBook." + Consts.Queries.getAll, query = "select ub FROM UserBook ub")
public class UserBook extends BaseEntity{

  @Column
  private User user;
  @Column
  private Book book;

  public UserBook(Long id, String title, User user, Book book) {
    super(id, title);
    this.user = user;
    this.book = book;
  }
}
