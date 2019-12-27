package com.example.travelcitybot;

import com.example.travelcitybot.service.TravelCityBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@SpringBootApplication
public class TravelCityBotApplication {

    public static void main ( String[] args ){
        ApiContextInitializer.init ( );
        SpringApplication.run (TravelCityBotApplication.class, args);

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi ( );
        try {
            telegramBotsApi.registerBot (new TravelCityBot ( ));

        } catch (TelegramApiException e) {
            e.printStackTrace ( );
        }
    }

}
