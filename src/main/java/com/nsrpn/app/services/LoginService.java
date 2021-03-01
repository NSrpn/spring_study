package com.nsrpn.app.services;

import com.nsrpn.app.config.AppSecurityConfig;
import com.nsrpn.app.entities.User;
import com.nsrpn.app.storage.IStorage;
import com.nsrpn.app.storage.UserStorage;
import com.nsrpn.web.forms.LoginEdit;
import com.nsrpn.web.forms.LoginForm;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements UserDetailsService {

  private Logger logger = Logger.getLogger(LoginService.class);

  private final IStorage<User> userRepo;

  @Autowired
  AppSecurityConfig secConfig;

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
    u.setPwd(secConfig.passwordEncoder().encode(loginEdit.getPassword()));
    u.setRole((loginEdit.getAdmin() != null && loginEdit.getAdmin()) ? "ADMIN" : "USER");
    u.setNeedPwdChange(loginEdit.getNeedChangePwd() != null ? loginEdit.getNeedChangePwd() : false);
    userRepo.save(u);
    return true;
  }

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    User u = ((UserStorage)userRepo).getByUserName(s);
    if (u == null) {
      throw new UsernameNotFoundException("Unknown user: " + s);
    }

    UserDetails user = org.springframework.security.core.userdetails.User.builder()
        .username(u.getUserName())
        .password(u.getPwd())
        .roles(u.getRole())
        .build();
    return user;
  }

  public boolean update(LoginEdit loginEdit) {
    User u = UserStorage.getInstance().getByUserName(loginEdit.getUsername());
    u.setTitle(loginEdit.getTitle());
    u.setUserName(loginEdit.getUsername());
    u.setPwd(secConfig.passwordEncoder().encode(loginEdit.getPassword()));
    u.setRole((loginEdit.getAdmin() != null && loginEdit.getAdmin()) ? "ADMIN" : "USER");
    u.setNeedPwdChange(loginEdit.getNeedChangePwd() != null ? loginEdit.getNeedChangePwd() : false);
    userRepo.save(u);
    return true;
  }
}
