package com.nsrpn.app.services;

import com.nsrpn.app.entities.UserBook;
import com.nsrpn.app.storage.IStorage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShelfService  {

  private final IStorage<UserBook> shelfRepo;

  @Autowired
  public ShelfService(IStorage<UserBook> shelfRepo) {
    this.shelfRepo = shelfRepo;
  }

  public List<UserBook> getAllBooks() {
    return shelfRepo.getAll();
  }

  public void saveBook(UserBook book) {
    shelfRepo.save(book);
  }

  public boolean removeBookById(Integer bookIdToRemove) {
    return shelfRepo.remove(null);
  }
}
