package com.nsrpn.app.exceptions;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;

public class AuthException extends Exception {

  private String message;
  private List<ObjectError> messages;

  public AuthException(List<ObjectError> messages) {
    super();
    this.messages = messages;
    setMessageFromSet();
  }

  public AuthException(String message) {
    super();
    this.message = message;
  }

  private void setMessageFromSet() {
    final StringBuffer sb = new StringBuffer();
    if (messages != null && !messages.isEmpty()) {
      messages.forEach(m -> {
        sb.append("\r\n")
            .append((m instanceof FieldError) ? ((FieldError) m).getField() : m.getObjectName()).append(": ").append(m.getDefaultMessage());
      });
      message = sb.substring(2);
    }
  }

  public List<ObjectError> getMessages() {
    return messages;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
