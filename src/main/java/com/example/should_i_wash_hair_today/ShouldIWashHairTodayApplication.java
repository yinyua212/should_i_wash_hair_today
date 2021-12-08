package com.example.should_i_wash_hair_today;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ShouldIWashHairTodayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShouldIWashHairTodayApplication.class, args);
    }
}
