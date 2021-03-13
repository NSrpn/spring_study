package com.nsrpn.app.exceptions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

  @Value("${exception.login}")
  private String msgBadCredentials;
  @Value("${exception.usernotfound}")
  private String msgUsernameNotFound;
  @Value("${exception.formisblank}")
  private String msgNotBlank;

  public AuthFailureHandler(String defaultFailureUrl) {
    super(defaultFailureUrl);
  }

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

    super.onAuthenticationFailure(request, response, exception);

    if(exception.getClass().isAssignableFrom(BadCredentialsException.class)) {
      saveException(request, new BadCredentialsException(msgBadCredentials));
    }

    if(exception.getClass().isAssignableFrom(UsernameNotFoundException.class)) {
      saveException(request, new UsernameNotFoundException(msgUsernameNotFound));
    }

    if(exception.getClass().isAssignableFrom(InternalAuthenticationServiceException.class)) {
      saveException(request, new InternalAuthenticationServiceException(msgNotBlank));
    }

  }

}