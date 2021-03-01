package com.nsrpn.app.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "com.nsrpn.app")
@PropertySource("classpath:fileStorage.properties")
public class AppContextConfig {

}
