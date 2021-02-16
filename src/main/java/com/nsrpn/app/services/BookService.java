package com.nsrpn.app.services;

import com.nsrpn.app.entities.Book;
import com.nsrpn.app.storage.BookStorage;
import com.nsrpn.app.storage.IStorage;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookService {

  private final IStorage<Book> bookRepo;

  public BookService() {
    this.bookRepo = BookStorage.getInstance();
  }

  public void saveBook(Book book) {
    bookRepo.save(book);
  }

  public boolean removeBookById(Long bookIdToRemove) {
    List<Book> bookIds = ((BookStorage)bookRepo)
                            .getAllBooks()
                            .stream()
                            .filter(b->b.getId().equals(bookIdToRemove))
                            .collect(Collectors.toList());
    bookRepo.remove(bookIds);
    return true;
  }

  public List<Book> getFiltered(String prefix, HttpSession session) {
    Map<String, String> attrs = (Map<String, String>)session.getAttribute("filter");
    return ((BookStorage)bookRepo)
              .getAllBooks()
              .stream()
              .filter(b -> b.matchFilter(prefix, attrs))
              .collect(Collectors.toList());
  }
}
