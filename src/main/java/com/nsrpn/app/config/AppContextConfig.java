package com.nsrpn.app.config;

import com.nsrpn.app.exceptions.AuthFailureHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "com.nsrpn.app")
@PropertySource("classpath:fileStorage.properties")
@PropertySource("classpath:Messages.properties")
public class AppContextConfig {

  @Bean
  public AuthFailureHandler authFailureHandler() {
    AuthFailureHandler handler = new AuthFailureHandler("/login");
    return handler;
  }
}
