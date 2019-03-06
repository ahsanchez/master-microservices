package com.ahs.master.microservices;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    
    private static final String CONTACT = "Ana Hinojosa";
    @SuppressWarnings("deprecation")
    private static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Documentación API",
            "Esta es la documentación del API",
            "1.0",
            "n/a",
            CONTACT,
            "Apache",
            "http://www.apache.org/licenses/LICENSE-2.0");
    
    private static final Set<String> DEAFULT_PRODUCES =
            new HashSet<>(Arrays.asList("application/json", "application/xml"));
    
    private static final Set<String> DEAFULT_CONSUMES =
            new HashSet<>(Arrays.asList("application/json", "application/xml"));
    
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)/*
                                                       * .apiInfo(
                                                       * DEFAULT_API_INFO).
                                                       * produces(
                                                       * DEAFULT_PRODUCES).
                                                       * consumes(
                                                       * DEAFULT_CONSUMES)
                                                       */;
    }
}
