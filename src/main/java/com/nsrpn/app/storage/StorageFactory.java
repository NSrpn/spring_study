package com.nsrpn.app.storage;

import com.nsrpn.app.entities.Book;
import com.nsrpn.app.entities.Files;
import com.nsrpn.app.entities.User;
import com.nsrpn.app.entities.UserBook;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class StorageFactory {

  private static SessionFactory sessionFactory;

  private StorageFactory() {}

  public static SessionFactory getSessionFactory() {
    if (sessionFactory == null) {
      try {
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(Book.class);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(UserBook.class);
        configuration.addAnnotatedClass(Files.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(builder.build());

      } catch (Exception e) {
        System.out.println("Исключение!" + e);
      }
    }
    return sessionFactory;
  }
}
