package com.nsrpn.app.storage;

import com.nsrpn.app.entities.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserStorage implements IStorage<User> {

  @Override
  public String getAllQuery() {
    return "select u FROM User u";
  }
}
