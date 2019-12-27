package com.example.travelcitybot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class TravelCityBotApplication {

    public static void main ( String[] args ){
        ApiContextInitializer.init ( );
        SpringApplication.run (TravelCityBotApplication.class, args);
    }
}
