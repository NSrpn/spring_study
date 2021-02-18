package com.nsrpn.web.validators;

import com.nsrpn.app.entities.Book;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BooksValidator implements Validator {
  @Override
  public boolean supports(Class<?> clazz) {
    return Book.class.equals(clazz);
  }

  public void validate(Object target, Errors errors) {
    Book p = (Book) target;

    if (p.getTitle().isEmpty()) {
      errors.rejectValue("title", "exception.notblank");
    }
    if (p.getAuthor().isEmpty()) {
      errors.rejectValue("author", "exception.notblank");
    }
    if (p.getSize() == null) {
      errors.rejectValue("size", "exception.notblank");
    }

  }
}