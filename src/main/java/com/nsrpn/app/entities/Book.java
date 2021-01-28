package com.nsrpn.app.entities;

import com.nsrpn.app.utils.Consts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Books")
@NamedQuery(name = "Book." + Consts.Queries.getAll, query = "select b FROM Book b")
public class Book extends BaseEntity {

  @Column
  private String author;
  @Column
  private Integer size;

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

}
