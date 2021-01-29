package com.nsrpn.app.storage;

import com.nsrpn.app.entities.BaseEntity;
import org.hibernate.Session;

import java.util.List;

public interface IStorage<T extends BaseEntity> {

  String getAllQuery();

  default List<T> getAll() {
    Session session = StorageFactory.getSessionFactory().openSession();
    return (List<T>) session.createQuery(getAllQuery()).list();
  }

  default boolean save(T obj) {
    Session session = StorageFactory.getSessionFactory().openSession();
    session.save(obj);
    return true;
  }

  default boolean remove(List<T> objs) {
    Session session = StorageFactory.getSessionFactory().openSession();
    for (T obj : objs)
      session.delete(obj);
    return true;
  }

}
