package com.example.travelcitybot.service;

import com.example.travelcitybot.domain.TelegramCity;
import com.google.common.base.Functions;
import lombok.extern.log4j.Log4j2;
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

    private final TelegramCityService telegramCityService;


    public TravelCityBot ( TelegramCityService telegramCityService ){
        this.telegramCityService = telegramCityService;
    }

    @Override
    public void onUpdateReceived ( Update update ){

        Message message = update.getMessage ( );
        List<TelegramCity> cars;
        if (message != null && message.hasText ( )) {
            cars = telegramCityService.findByTelegramCity (message.getText ( ));
            if (cars != null && !cars.isEmpty ( )) {
                String[] array = cars.stream ( ).map (Functions.toStringFunction ( )).toArray (String[]::new);
                String result = array[0].replaceAll("descriptionCity=", "");
                String result1 = result.replaceAll("telegramCity=", "");
                String result2 = result1.replaceAll("id=", "");
                String result3 = result2.replaceAll("TelegramCity", "");
                String result4 = result3.replaceAll("'", "");
                String result5 = result4.replaceAll("}", "");
                String result6 = result5.substring(3, result5.length()-1);
                sendTextMessage (message,  result6);
            } else {
                sendTextMessage (message, "Извените у нас нет такого города в Базе!");
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
            e.printStackTrace ( );
        }
    }


    @Override
    public String getBotUsername (){
        return "Travel_City_Bot";
    }

    @Override
    public String getBotToken (){
        return "980163273:AAEOzFgrciPb6V6Nvja3Np1n3aG6Uw1yj-U";
    }
}
