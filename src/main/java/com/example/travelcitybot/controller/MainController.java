package com.example.travelcitybot.controller;


import com.example.travelcitybot.domain.TelegramCity;
import com.example.travelcitybot.service.TelegramCityService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class MainController {

    private final TelegramCityService telegramCityService;

    public MainController ( TelegramCityService telegramCityService ){
        this.telegramCityService = telegramCityService;
    }

    @RequestMapping(path = "/api/cities", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8", produces = "application/json")
    public ResponseEntity<Void> createTelegramCity ( @Valid @RequestBody TelegramCity request ){
        try {
            telegramCityService.addTelegramCity (request);
            return ResponseEntity.noContent ( ).build ( );
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status (HttpStatus.CONFLICT).build ( );
        }
    }

    @RequestMapping(path = "/api/cities/{id}", method = RequestMethod.PUT, consumes = "application/json;charset=UTF-8", produces = "application/json")
    public ResponseEntity<Void> updateTelegramCity ( @RequestBody TelegramCity request, @PathVariable long id ){
        try {
            request.setId (id);
            telegramCityService.updateTelegramCity (request);
            return ResponseEntity.noContent ( ).build ( );
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound ( ).build ( );

        }
    }

    @RequestMapping(path = "/api/cities/{id}", method = RequestMethod.DELETE, consumes = "application/json;charset=UTF-8", produces = "application/json")
    public ResponseEntity<Void> deleteTelegramCity ( @PathVariable long id ){
        try {
            telegramCityService.deleteTelegramCity (id);
            return ResponseEntity.noContent ( ).build ( );
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound ( ).build ( );
        }
    }
}
