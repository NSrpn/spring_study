package com.nsrpn.app.storage;

import com.nsrpn.app.entities.Files;
import org.hibernate.Session;

import java.util.List;

public class DBFilesStorage implements IStorage<Files> {

  @Override
  public String getAllQuery() {
    return "select f from Files f";
  }

  public Files getByEntity(String entityClass, Long entityId) {
    Session session = StorageFactory.getSessionFactory().openSession();
    List<Files> fileList =
        session
            .createNamedQuery("getFilesByEntity", Files.class)
            .setParameter("cl", entityClass)
            .setParameter("id", entityId)
            .list();
    session.close();
    return fileList.isEmpty() ? null : fileList.get(0);
  }

}
