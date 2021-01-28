package com.nsrpn.app.storage;

import com.nsrpn.app.entities.Book;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BookStorage implements IStorage<Book> {

  @Override
  public List<Book> get() {
    return null;
  }

  @Override
  public boolean remove(Map<String, String> params) {
    return false;
  }
}
