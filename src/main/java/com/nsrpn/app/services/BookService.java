package com.nsrpn.app.services;

import com.nsrpn.app.entities.Book;
import com.nsrpn.app.storage.IStorage;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO:
 * 1. Books Dict List
 * 1.1. Title
 * 1.2. Author
 * 1.3. Size
 * 1.4. Btns: Save/Delete/Close
 * 2 Main form
 * 2.1. Books List
 * 2.2. Menu: Edit user
 * 2.3. Btn: Logout
 */

@Service
public class BookService {

  @Autowired
  private SessionFactory sessionFactory;

  private final IStorage<Book> bookRepo;

  @Autowired
  public BookService(IStorage<Book> bookRepo) {
    this.bookRepo = bookRepo;
  }

  public List<Book> getAllBooks() {
    return bookRepo.get();
  }

  public void saveBook(Book book) {
    bookRepo.save(sessionFactory, book);
  }

  public boolean removeBookById(Integer bookIdToRemove) {
    return bookRepo.remove(null);
  }
}
