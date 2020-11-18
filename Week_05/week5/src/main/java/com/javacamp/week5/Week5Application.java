package com.javacamp.week5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class Week5Application {

    public static void main(String[] args) {
        SpringApplication.run(Week5Application.class, args);
    }

}
