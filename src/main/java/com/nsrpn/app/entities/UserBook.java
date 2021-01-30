package com.nsrpn.app.entities;

import com.nsrpn.app.utils.Consts;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="UserBooks")
@NamedQuery(name = "UserBook." + Consts.Queries.getAll, query = "select ub FROM UserBook ub")
public class UserBook extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne
  @JoinColumn(name = "book_id", nullable = false)
  private Book book;

  public UserBook(Long id, String title, User user, Book book) {
    super(id, title);
    this.user = user;
    this.book = book;
  }
}
