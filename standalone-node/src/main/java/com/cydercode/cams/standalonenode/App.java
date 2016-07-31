package com.cydercode.cams.standalonenode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @author Adam Tomaja
 */
@SpringBootApplication
public class App extends SpringBootServletInitializer {

    public static final Class<App> APP_CLASS = App.class;

    public static void main(String[] args) {
        SpringApplication.run(APP_CLASS, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(APP_CLASS);
    }
}
