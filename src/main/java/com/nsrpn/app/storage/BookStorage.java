package com.nsrpn.app.storage;

import com.nsrpn.app.entities.Book;
import org.springframework.stereotype.Repository;

@Repository
public class BookStorage implements IStorage<Book> {

  @Override
  public String getAllQuery() {
    return "select b FROM Book b";
  }
}
