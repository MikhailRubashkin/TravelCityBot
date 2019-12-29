package com.example.travelcitybot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class TravelCityBotApplication {

    public static void main ( String[] args ){
        SpringApplication.run (TravelCityBotApplication.class, args);
    }
    @PostConstruct
    public void init(){
        ApiContextInitializer.init();
    }
}
