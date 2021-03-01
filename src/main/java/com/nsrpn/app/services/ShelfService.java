package com.nsrpn.app.services;

import com.nsrpn.app.entities.Book;
import com.nsrpn.app.entities.User;
import com.nsrpn.app.entities.UserBook;
import com.nsrpn.app.storage.BookStorage;
import com.nsrpn.app.storage.IStorage;
import com.nsrpn.app.storage.UserStorage;
import com.nsrpn.app.utils.Consts;
import com.nsrpn.app.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ShelfService {

  private final IStorage<UserBook> shelfRepo;
  private List<UserBook> allBooks;

  @Autowired
  public ShelfService(IStorage<UserBook> shelfRepo) {
    this.shelfRepo = shelfRepo;
    getAllBooks();
  }

  public List<UserBook> getAllBooks() {
    allBooks = shelfRepo.getAll();
    BookStorage.getInstance().rereadBooks();
    return allBooks;
  }

  public void saveBook(String userName, Long bookId) {
    User u = UserStorage.getInstance().getByUserName(userName);
    Book b = BookStorage.getInstance().getById(bookId);
    UserBook ub = new UserBook(u, b);
    shelfRepo.save(ub);
    getAllBooks();
  }

  public boolean removeBookById(String userName, Long bookIdToRemove) {
    List<UserBook> bookIds =
        allBooks.stream()
          .filter(ub->ub.getBook().getId().equals(bookIdToRemove) && ub.getUser().getUserName().equals(userName))
          .collect(Collectors.toList());
    shelfRepo.remove(bookIds);
    getAllBooks();
    return true;
  }

  public List<Book> getBooksByUserName(String prefix, HttpSession session) {
    Map<String, String> attrs = (Map<String, String>)session.getAttribute("filter");
    String userName = Utils.getUserNameFromSession();
    return BookStorage.getInstance().getAllBooks()
                        .stream()
                        .filter(b -> b.getUsers()
                                      .stream()
                                      .anyMatch(ub -> ub.getUser().getUserName().equals(userName)))
                        .filter(b -> b.matchFilter(prefix, attrs))
                        .collect(Collectors.toList());
  }

  public List<UserBook> getFiltered(String prefix, HttpSession session) {
    if (allBooks == null) getAllBooks();
    Map<String, String> attrs =
        session.getAttribute("filter") != null ? (Map<String, String>)session.getAttribute("filter") : new HashMap<>();
    attrs.put(Consts.Web.userSessionName, Utils.getUserNameFromSession());
    return allBooks.stream().
            filter(ub -> ub.matchFilter(prefix, attrs)).collect(Collectors.toList());
  }

}
