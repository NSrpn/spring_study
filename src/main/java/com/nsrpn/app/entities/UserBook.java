package com.nsrpn.app.entities;

import com.nsrpn.app.utils.Consts;
import com.nsrpn.app.utils.Utils;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name="UserBooks",
    indexes = {
        @Index(name = "idx_userbooks_userid",  columnList="user_id")
    },
    uniqueConstraints = {
      @UniqueConstraint(name = "uq_userBook_user_book", columnNames = {"user_id", "book_id"})
    }
)
@NamedQueries(
  {
    @NamedQuery(name = "getBooksByUserId", query = "select b FROM Book b join UserBook ub on b.id = ub.book.id where ub.user.id = :u")
  }
)
public class UserBook extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne
  @JoinColumn(name = "book_id", nullable = false)
  private Book book;

  public UserBook() {
    super();
  }

  public UserBook(User u, Book b) {
    setUser(u);
    setBook(b);
    setTitle(b.getTitle());
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }

  @Override
  public boolean matchFilter(String prefix, Map<String, String> params) {
    if (params != null) {
      if ((!getUser().getId().toString().equals(params.get(Consts.Web.userSessionName))) ||
          (!Utils.compareFieldValueWithFilter(params.getOrDefault(prefix + "id", ""), getBook().getId().toString())
           || !Utils.compareFieldValueWithFilter(params.getOrDefault(prefix + "title",""), getBook().getTitle())
           || !Utils.compareFieldValueWithFilter(params.getOrDefault(prefix + "author", ""), getBook().getAuthor()))
      )
        return false;
    }
    return true;
  }
}
