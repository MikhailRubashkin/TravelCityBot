package com.example.travelcitybot.service;

import com.example.travelcitybot.domain.TelegramCity;
import com.google.common.base.Functions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Component
@PropertySource("classpath:telegram.properties")
public class TravelCityBot extends TelegramLongPollingBot {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory
            .getLogger (TravelCityBot.class);

    @Value("${bot.name}")
    private String botName;
    @Value("${bot.token}")
    private String botToken;

    private final TelegramCityService telegramCityService;

    public TravelCityBot ( TelegramCityService telegramCityService ){
        this.telegramCityService = telegramCityService;
    }

    @Override
    public void onUpdateReceived ( Update update ){

        Message message = update.getMessage ( );
        List<TelegramCity> cities;
        if (message.getText ( ).equals ("/Start")) {
            sendTextMessage (message, "Здравствуйте! Введите пожалуйста город: ");
        } else if (message.hasText ( )) {
            cities = telegramCityService.findByTelegramCity (message.getText ( ));
            if (cities != null && !cities.isEmpty ( )) {
                String[] array = cities.stream ( ).map (Functions.toStringFunction ( )).toArray (String[]::new);
                String result = array[0].replaceAll ("descriptionCity=", "");
                String result1 = result.replaceAll ("telegramCity=", "");
                String result2 = result1.replaceAll ("id=", "");
                String result3 = result2.replaceAll ("TelegramCity", "");
                String result4 = result3.replaceAll ("'", "");
                String result5 = result4.replaceAll ("}", "");
                String result6 = result5.substring (3, result5.length ( )-1);
                sendTextMessage (message, result6);
            } else {
                sendTextMessage (message, "Извените у нас нет такого города в базе данных!");
            }
        }
    }

    private synchronized void sendTextMessage ( Message message, String text ){
        SendMessage sendMessage = new SendMessage ( );
        sendMessage.enableMarkdown (true);
        sendMessage.setChatId (message.getChatId ( ).toString ( ));
        sendMessage.setReplyToMessageId (message.getMessageId ( ));
        sendMessage.setText (text);
        try {
            execute (sendMessage);
        } catch (TelegramApiException e) {
            log.error ("SendMessage error", e);
        }
    }

    @Override
    public String getBotUsername (){
        return botName;
    }

    @Override
    public String getBotToken (){
        return botToken;
    }
}
