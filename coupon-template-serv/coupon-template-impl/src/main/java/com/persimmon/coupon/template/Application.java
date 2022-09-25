package com.persimmon.coupon.template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author persimmon
 */
@SpringBootApplication
@EnableJpaAuditing
@ComponentScan(basePackages = "com.persimmon")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
