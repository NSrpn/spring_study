package com.nsrpn.app.entities;

import com.nsrpn.app.utils.Utils;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Entity
@Table(name = "Books")
public class Book extends BaseEntity {

  @Column
  private String author;
  @Column
  private Integer size;

  @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  private Set<UserBook> users;

  public Book() {
    super();
  }

  public Book(Long id, String title, String author, Integer size) {
    super(id, title);
    this.author = author;
    this.size = size;
  }

  public String getAuthor() {
    return author;
  }

  public Integer getSize() {
    return size;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public void setSize(Integer size) {
    this.size = size;
  }

  public Set<UserBook> getUsers() {
    return users;
  }

  public void setUsers(Set<UserBook> users) {
    this.users = users;
  }

  public static Map<String, String> getFilter(String prefix, HttpServletRequest rq) {
    Map<String, String> map = new HashMap<>();
    map.put(prefix + "id", rq != null ? rq.getParameter(prefix + "id") + "" : "");
    map.put(prefix + "title", rq != null ? rq.getParameter(prefix + "title") + "" : "");
    map.put(prefix + "author", rq != null ? rq.getParameter(prefix + "author") + "" : "");
    return map;
  }

  @Override
  public boolean matchFilter(String prefix, Map<String, String> params) {
    return (params == null) ||
           (Utils.compareFieldValueWithFilter(params.getOrDefault(prefix + "id", ""), getId().toString())
              && Utils.compareFieldValueWithFilter(params.getOrDefault(prefix + "title",""), getTitle())
              && Utils.compareFieldValueWithFilter(params.getOrDefault(prefix + "author", ""), getAuthor())
           );
  }
}
