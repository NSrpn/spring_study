package com.nsrpn.web.validators;

import com.nsrpn.web.forms.BookEdit;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BooksValidator implements Validator {
  @Override
  public boolean supports(Class<?> clazz) {
    return BookEdit.class.equals(clazz);
  }

  public void validate(Object target, Errors errors) {
    BookEdit p = (BookEdit) target;

    if (p.getTitle().isEmpty()) {
      errors.rejectValue("title", "exception.notblank");
    }
    if (p.getAuthor().isEmpty()) {
      errors.rejectValue("author", "exception.notblank");
    }
  }
}