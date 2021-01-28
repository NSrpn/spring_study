package com.nsrpn.app.storage;

import com.nsrpn.app.entities.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UserStorage implements IStorage<User> {

  @Override
  public List<User> get() {
    return null;
  }

  @Override
  public boolean remove(Map<String, String> params) {
    return false;
  }
}
