package com.nsrpn.app.storage;

import com.nsrpn.app.entities.UserBook;
import org.springframework.stereotype.Repository;

@Repository
public class ShelfStorage  implements IStorage<UserBook> {

  @Override
  public String getAllQuery() {
    return "select ub FROM UserBook ub";
  }

}
