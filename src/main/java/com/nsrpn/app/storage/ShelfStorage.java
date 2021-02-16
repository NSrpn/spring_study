package com.nsrpn.app.storage;

import com.nsrpn.app.entities.Book;
import com.nsrpn.app.entities.UserBook;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShelfStorage  implements IStorage<UserBook> {

  @Override
  public String getAllQuery() {
    return "select ub FROM UserBook ub";
  }

}
