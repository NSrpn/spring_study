package com.nsrpn.app.entities;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "Files")
@NamedQueries(
    {
        @NamedQuery(name = "getFilesByEntity", query = "SELECT f FROM Files f WHERE f.objectClass = :cl and f.objectId = :id")}
)
public class Files extends BaseEntity {

  @Column
  private String objectClass;

  @Column
  private Long objectId;

  /**
  * В одном случае мы храним ссылку, в другом - содержание
  */
  @Lob
  private String fileData;

  public Files() {
    super();
  }

  public Files(BaseEntity entity) {
    this();
    setObjectId(entity.getId());
    setObjectClass(entity.getClass().getName());
  }

  public String getFileData() {
    return fileData;
  }

  public void setFileData(String fileData) {
    this.fileData = fileData;
  }

  public String getObjectClass() {
    return objectClass;
  }

  public void setObjectClass(String objectClass) {
    this.objectClass = objectClass;
  }

  public Long getObjectId() {
    return objectId;
  }

  public void setObjectId(Long objectId) {
    this.objectId = objectId;
  }


}
