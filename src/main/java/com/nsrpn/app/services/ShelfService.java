package com.nsrpn.app.services;

import com.nsrpn.app.entities.UserBook;
import com.nsrpn.app.storage.IStorage;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShelfService  {

  @Autowired
  private SessionFactory sessionFactory;

  private final IStorage<UserBook> shelfRepo;

  @Autowired
  public ShelfService(IStorage<UserBook> shelfRepo) {
    this.shelfRepo = shelfRepo;
  }

  public List<UserBook> getAllBooks() {
    return shelfRepo.get();
  }

  public void saveBook(UserBook book) {
    shelfRepo.save(sessionFactory, book);
  }

  public boolean removeBookById(Integer bookIdToRemove) {
    return shelfRepo.remove(null);
  }
}
