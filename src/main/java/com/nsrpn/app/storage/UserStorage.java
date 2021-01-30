package com.nsrpn.app.storage;

import com.nsrpn.app.entities.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedQuery;
import javax.persistence.Parameter;
import java.util.List;

@Repository
public class UserStorage implements IStorage<User> {

  @Override
  public String getAllQuery() {
    return "select u FROM User u";
  }

  /**
   * Get by UserName. UserName is unique field.
   * @param userName
   * @return User
   */
  public User getByUserName(String userName) {
    Session session = StorageFactory.getSessionFactory().openSession();
    List<User> userList = session.createNamedQuery("getUserByName", User.class).setParameter("p", userName).list();
    session.close();
    return userList.isEmpty() ? null : userList.get(0);
  }

}
