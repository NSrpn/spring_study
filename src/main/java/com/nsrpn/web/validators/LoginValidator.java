package com.nsrpn.web.validators;

import com.nsrpn.web.forms.LoginEdit;
import com.nsrpn.web.forms.LoginForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class LoginValidator implements Validator {
  @Override
  public boolean supports(Class<?> clazz) {
    return LoginEdit.class.equals(clazz) || LoginForm.class.equals(clazz);
  }

  public void validate(Object target, Errors errors) {
    LoginForm p = (LoginForm) target;

    if (p.getUsername().isEmpty()) {
      errors.rejectValue("username", "exception.notblank");
    }
    if (p.getPassword().isEmpty()) {
      errors.rejectValue("password", "exception.notblank");
    }
    if (target instanceof LoginEdit) {
      if (((LoginEdit) target).getTitle().isEmpty()) {
        errors.rejectValue("title", "exception.notblank");
      }
    }

  }
}
