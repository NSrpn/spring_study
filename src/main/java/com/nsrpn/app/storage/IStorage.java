package com.nsrpn.app.storage;

import com.nsrpn.app.entities.BaseEntity;
import org.hibernate.Session;
import java.util.List;

public interface IStorage<T extends BaseEntity> {

  String getAllQuery();

  default List<T> getAll() {
    Session session = StorageFactory.getSessionFactory().openSession();
    List<T> res = (List<T>) session.createQuery(getAllQuery()).list();
    session.close();
    return res;
  }

  default boolean save(T obj) {
    Session session = StorageFactory.getSessionFactory().openSession();
    session.beginTransaction();
    session.save(obj);
    session.getTransaction().commit();
    session.close();
    return true;
  }

  default boolean remove(List<T> objs) {
    Session session = StorageFactory.getSessionFactory().openSession();
    session.beginTransaction();
    for (T obj : objs)
      session.delete(obj);
    session.getTransaction().commit();
    session.close();
    return true;
  }

  default T getById(Long id) {
    Session session = StorageFactory.getSessionFactory().openSession();
    List<T> res = (List<T>)session.createQuery(getAllQuery() + " where id = :id").setParameter("id", id).list();
    session.close();
    return (!res.isEmpty()) ? res.get(0) : null;
  }

}
