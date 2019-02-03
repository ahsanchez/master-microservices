package com.ahs.master.microservices;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@SpringBootApplication
public class MasterMicroservicesApplication {
    
    public static void main(
        String[] args){
        SpringApplication.run(MasterMicroservicesApplication.class, args);
    }
    
    @Bean
    public LocaleResolver localeResolver(){
        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        localeResolver.setDefaultLocale(Locale.US);
        return localeResolver;
    }
    
    // Equivalente a la propiedad spring.messages.basename
    // @Bean
    // public ResourceBundleMessageSource messageSource(){
    // ResourceBundleMessageSource messageSource = new
    // ResourceBundleMessageSource();
    // messageSource.addBasenames("messages");
    // return messageSource;
    // }
}
