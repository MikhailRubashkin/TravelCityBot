package com.example.travelcitybot.service;

import com.example.travelcitybot.domain.TelegramCity;
import com.google.common.base.Functions;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;


@Log4j2
@Component
public class TravelCityBot extends TelegramLongPollingBot {

    @Autowired
    private TelegramCityService telegramCityService;

    @Override
    public void onUpdateReceived ( Update update ){

        Message message = update.getMessage ( );
        List<TelegramCity> cars;
        if (message != null && message.hasText ( )) {
            cars = telegramCityService.findByTelegramCity (message.getText ( ));
            if (cars != null && !cars.isEmpty ( )) {
                String[] array = cars.stream ( ).map (Functions.toStringFunction ( )).toArray (String[]::new);
                String result = array[0].substring(32, array[0].length()-1);
                sendTextMessage (message,  result);
            } else {
                sendTextMessage (message, "Извените у нас нет такого города в Базе!");
            }
        } else {
            sendTextMessage (message, "Извените у нас нет такого города в Базе!");
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
            e.printStackTrace ( );
        }
    }


    @Override
    public String getBotUsername (){
        return "";
    }

    @Override
    public String getBotToken (){
        return "";
    }
}
