package com.nsrpn.app.services;

import com.nsrpn.app.entities.User;
import com.nsrpn.app.storage.IStorage;
import com.nsrpn.web.forms.LoginEdit;
import com.nsrpn.web.forms.LoginForm;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO:
 * 1. Add user
 * 1.1. Add UserForm (New)
 * 1.1.1. Name (Title)
 * 1.1.2. UserName
 * 1.1.3. Pwd
 * 1.1.4. Btns: Save/Delete/Close
 * 1.2. Save User
 * 2. Auth
 * 2.1. Add Auth Form
 * 2.2. Open Books Form
 * 3. Edit user
 * 3.1. Open User form
 * 3.2. Enter Pwd
 * 3.2. Save User
 * 4. Delete User
 * 4.1. Only in User from
 * 4.2. Enter Pwd
 * 4.3. System Exit
 */

@Service
public class LoginService {

  private Logger logger = Logger.getLogger(LoginService.class);

  private final IStorage<User> userRepo;

  @Autowired
  public LoginService(IStorage<User> userRepo) {
    this.userRepo = userRepo;
  }

  public boolean authenticate(LoginForm loginForm) {
    logger.info("try auth with user-form: " + loginForm);
    return loginForm.getUsername().equals("root") && loginForm.getPassword().equals("123");
  }

  public boolean register(LoginEdit loginEdit) {
    User u = new User();
    u.setTitle(loginEdit.getTitle());
    u.setUserName(loginEdit.getUsername());
    u.setPwd(loginEdit.getPassword());
    userRepo.save(u);
    return true;
  }
}
