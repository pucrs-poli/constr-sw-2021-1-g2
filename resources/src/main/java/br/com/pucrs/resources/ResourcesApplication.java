package br.com.pucrs.resources;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ResourcesApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ResourcesApplication.class, args);
    }

}
