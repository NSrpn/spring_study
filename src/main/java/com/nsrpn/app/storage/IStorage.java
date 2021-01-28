package com.nsrpn.app.storage;

import com.nsrpn.app.entities.BaseEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Map;

public interface IStorage<T extends BaseEntity> {

  List<T> get();

  default boolean save(SessionFactory sf, T obj) {
    Session session = sf.openSession();
    session.save(obj);
    return true;
  }

  boolean remove(Map<String, String> params);

}
