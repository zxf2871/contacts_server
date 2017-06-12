package com.sectong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by Administrator on 2017/6/7.
 */

@SpringBootApplication
public class B8A3StartApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(B8A3StartApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(B8A3StartApplication.class, args);
    }

}
