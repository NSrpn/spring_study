package com.nsrpn.app.entities;

import javax.persistence.*;
import java.lang.reflect.Field;

@MappedSuperclass
public class BaseEntity {
  @Id
  @GeneratedValue
  private Long id;

  @Column
  private String title;

  public BaseEntity() {
  }

  public BaseEntity(Long id, String title) {
    this.id = id;
    this.title = title;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String toString() {
    StringBuffer sb = new StringBuffer(getClass().getName());
    for (Field f : getClass().getDeclaredFields()) {
      sb.append(f.getName() + "=");
      if (f.isAccessible()) {
        try {
          sb.append(f.get(this).toString());
        } catch (IllegalAccessException e) {
          sb.append("error of value getter");
        }
      }
      sb.append("}");
    }
    return sb.toString();
  }

}
