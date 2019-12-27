package com.example.travelcitybot.service;

import lombok.extern.log4j.Log4j2;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Log4j2

public class TravelCityBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived ( Update update ){

    }

    public synchronized void sendTextMessage ( Message message, String text ){
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
