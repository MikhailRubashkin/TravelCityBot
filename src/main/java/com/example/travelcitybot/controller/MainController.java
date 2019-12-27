package com.example.travelcitybot.controller;


import com.example.travelcitybot.domain.TelegramCity;
import com.example.travelcitybot.service.TelegramCityService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class MainController {

    private final TelegramCityService telegramCityService;

    public MainController ( TelegramCityService telegramCityService ){
        this.telegramCityService = telegramCityService;
    }

    @PostMapping(value = "/api/cities", consumes = "application/json;charset=UTF-8", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTelegramCity ( @Valid @RequestBody TelegramCity request ){
        telegramCityService.addTelegramCity (request);
    }

   @RequestMapping(path = "/api/cities/{id}", method = RequestMethod.PUT, consumes = "application/json;charset=UTF-8", produces = "application/json")
    public ResponseEntity<Void> updateTelegramCity ( @RequestBody TelegramCity request, @PathVariable long id ){
       try {
           request.setId (id);
           telegramCityService.updateTelegramCity (request);
           return ResponseEntity.noContent ().build ();
       } catch (ResourceNotFoundException e) {
           return ResponseEntity.notFound ( ).build ( );

       }
   }

    @DeleteMapping(value = "/api/cities/{id}", consumes = "application/json;charset=UTF-8", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void deleteTelegramCity ( @PathVariable long id, @Valid @RequestBody TelegramCity request ){
        telegramCityService.deleteTelegramCity (id);
    }
}
