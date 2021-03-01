package com.nsrpn.web.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@Configuration
@ComponentScan(basePackages = "com.nsrpn.web")
@EnableWebMvc
public class WebContextConfig implements WebMvcConfigurer {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/**").addResourceLocations("classpath:images");
  }

  @Bean(value="messageSource")
  public ResourceBundleMessageSource messageSource() {
    ResourceBundleMessageSource res = new ResourceBundleMessageSource();
    res.setBasename("Messages");
    return res;
  }

  @Bean
  public SpringResourceTemplateResolver templateResolver() {
    SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
    templateResolver.setPrefix("/WEB-INF/views/");
    templateResolver.setSuffix(".html");
    templateResolver.setCacheable(true);
    templateResolver.setCharacterEncoding("UTF-8");
    templateResolver.setTemplateMode("HTML5");
    return templateResolver;
  }

  @Bean
  public SpringTemplateEngine templateEngine() {
    SpringTemplateEngine eng = new SpringTemplateEngine();
    eng.setEnableSpringELCompiler(true);
    eng.setTemplateResolver(templateResolver());
    return eng;
  }

  @Bean
  public ThymeleafViewResolver viewResolver() {
    ThymeleafViewResolver res = new ThymeleafViewResolver();
    res.setOrder(1);
    res.setCharacterEncoding("UTF-8");
    res.setContentType("text/html; charset=UTF-8");
    res.setTemplateEngine(templateEngine());
    return res;
  }

  @Bean
  public CommonsMultipartResolver multipartResolver() {
    CommonsMultipartResolver res = new CommonsMultipartResolver();
    res.setMaxUploadSize(5000000);
    return res;
  }
}
