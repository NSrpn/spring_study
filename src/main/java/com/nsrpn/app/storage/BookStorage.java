package com.nsrpn.app.storage;

import com.nsrpn.app.entities.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookStorage implements IStorage<Book> {

  private static BookStorage bookStorage;
  private List<Book> allBooks;

  public static BookStorage getInstance() {
    if (bookStorage == null)
      bookStorage = new BookStorage();
    return bookStorage;
  }

  public BookStorage() {
    rereadBooks();
  }

  public synchronized void rereadBooks() {
    allBooks = getAll();
  }

  public List<Book> getAllBooks() {
    return allBooks;
  }

  @Override
  public boolean save(Book obj) {
    boolean res = IStorage.super.save(obj);
    if (res)
      rereadBooks();
    return res;
  }

  @Override
  public boolean remove(List<Book> objs) {
    boolean res = IStorage.super.remove(objs);
    if (res)
      rereadBooks();
    return res;
  }

  @Override
  public String getAllQuery() {
    return "select b FROM Book b";
  }

}
