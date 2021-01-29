package com.nsrpn.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Books")
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
