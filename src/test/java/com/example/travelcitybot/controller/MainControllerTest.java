package com.example.travelcitybot.controller;

import com.example.travelcitybot.domain.TelegramCity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MainControllerTest {

    @Autowired
    private MainController mainController;

    @Autowired
    private MockMvc mockMvc;

    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType (MediaType.APPLICATION_JSON.getType ( ), MediaType.APPLICATION_JSON.getSubtype ( ), Charset.forName ("utf8"));

    @Test
    public void createTelegramCity () throws Exception{
        String url = "/api/cities";
        TelegramCity telegramCity = new TelegramCity ( );
        telegramCity.setTelegramCity ("Витебск");
        telegramCity.setDescriptionCity ("тоже очень красивый город!");

        ObjectMapper mapper = new ObjectMapper ( );
        mapper.configure (SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer ( ).withDefaultPrettyPrinter ( );
        String requestJson = ow.writeValueAsString (telegramCity);

        mockMvc.perform (MockMvcRequestBuilders.post (url).contentType (APPLICATION_JSON_UTF8)
                                 .content (requestJson))
                .andExpect (status ( ).isNoContent ( ));
    }


    @Test
    public void updateTelegramCity ()throws Exception{
        String url = "/api/cities/4";
        TelegramCity telegramCity = new TelegramCity ( );
        telegramCity.setTelegramCity ("Минск");
        telegramCity.setDescriptionCity ("виликолепный город!");

        ObjectMapper mapper = new ObjectMapper ( );
        mapper.configure (SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer ( ).withDefaultPrettyPrinter ( );
        String requestJson = ow.writeValueAsString (telegramCity);

        mockMvc.perform (MockMvcRequestBuilders.put (url).contentType (APPLICATION_JSON_UTF8)
                                 .content (requestJson))
                .andExpect (status ( ).isNoContent ());

    }

    @Test
    public void deleteTelegramCity () throws Exception{
        String url = "/api/cities/7";
        TelegramCity telegramCity = new TelegramCity ( );
        ObjectMapper mapper = new ObjectMapper ( );
        mapper.configure (SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer ( ).withDefaultPrettyPrinter ( );
        String requestJson = ow.writeValueAsString (telegramCity);

        mockMvc.perform (MockMvcRequestBuilders.delete (url).contentType (APPLICATION_JSON_UTF8)
                                 .content (requestJson))
                .andExpect (status ( ).isNoContent ());
    }
}