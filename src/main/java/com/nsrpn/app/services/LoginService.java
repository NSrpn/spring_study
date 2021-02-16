package com.nsrpn.app.services;

import com.nsrpn.app.entities.User;
import com.nsrpn.app.storage.IStorage;
import com.nsrpn.app.storage.UserStorage;
import com.nsrpn.web.forms.LoginEdit;
import com.nsrpn.web.forms.LoginForm;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

  private Logger logger = Logger.getLogger(LoginService.class);

  private final IStorage<User> userRepo;

  @Autowired
  public LoginService(IStorage<User> userRepo) {
    this.userRepo = userRepo;
  }

  public Long authenticate(LoginForm loginForm) {
    logger.info("try auth with user-form: " + loginForm);
    User u = ((UserStorage)userRepo).getByUserName(loginForm.getUsername());
    return (u != null) && u.getPwd().equals(loginForm.getPassword()) ? u.getId() : null;
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
