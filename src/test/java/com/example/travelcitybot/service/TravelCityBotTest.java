package com.example.travelcitybot.service;

import com.example.travelcitybot.domain.TelegramCity;
import com.google.common.base.Functions;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TravelCityBotTest{

    @Autowired
    private TelegramCityService telegramCityService;


    @Test
    public void onUpdateReceived () {
        String s = "Минск";
        List<TelegramCity> cities;
        cities = telegramCityService.findByTelegramCity (s);
        Assertions.assertNotNull (cities);
        String[] array = cities.stream ( ).map (Functions.toStringFunction ( )).toArray (String[]::new);
        String result = array[0];
        Assertions.assertEquals ("TelegramCity{id=4, telegramCity='Минск', descriptionCity='виликолепный город!'}", result);
        Assertions.assertNotEquals ("TelegramCity{id=4, telegramCity='Баку', descriptionCity='виликолепный город!}'", result);
    }
}