package com.ahs.master.microservices.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    
    @Autowired
    private MessageSource messageSource;
    
    @GetMapping("/hello-world")
    public String HelloWorld(){
        return "Hello World!";
    }
    
    @GetMapping("/hello-world-bean")
    public HelloWorldBean HelloWorldBean(){
        return new HelloWorldBean("Hello World");
    }
    
    @GetMapping("/hello-world-bean/path-variable/{name}")
    public HelloWorldBean HelloWorldPathVariable(
        @PathVariable String name){
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }
    
    // @GetMapping("/hello-world-internationalized")
    // public String HelloWorldInternationalized(
    // @RequestHeader(name = "Accept-Language", required = false) Locale
    // locale){
    // return messageSource.getMessage("good.morning.message", null, locale);
    // }
    
    @GetMapping("/hello-world-internationalized")
    public String HelloWorldInternationalized(){
        return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
    }
}
