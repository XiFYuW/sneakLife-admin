package com.sneaklife;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication()
//@EnableAspectJAutoProxy(exposeProxy = true)
public class SneakLifeAdminApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SneakLifeAdminApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SneakLifeAdminApplication.class, args);
    }
}
