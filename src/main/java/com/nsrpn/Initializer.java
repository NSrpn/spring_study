package com.nsrpn;

import com.nsrpn.app.config.AppContextConfig;
import com.nsrpn.web.config.WebContextConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class Initializer implements WebApplicationInitializer {
  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
    appContext.register(AppContextConfig.class);
    servletContext.addListener(new ContextLoaderListener(appContext));
    AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
    webContext.register(WebContextConfig.class);
    DispatcherServlet dispatcherServlet = new DispatcherServlet(webContext);
    ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", dispatcherServlet);
    dispatcher.setLoadOnStartup(1);
    dispatcher.addMapping("/");
  }
}
