package com.example.travelcitybot.controller;


import com.example.travelcitybot.domain.TelegramCity;
import com.example.travelcitybot.service.TelegramCityService;
import org.springframework.http.HttpStatus;
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

    @PutMapping(value = "/api/cities/{id}", consumes = "application/json;charset=UTF-8", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateTelegramCity ( @PathVariable Long id, @Valid @RequestBody TelegramCity request ){
        telegramCityService.updateTelegramCity (request);
    }

    @DeleteMapping(value = "/api/cities/{id}", consumes = "application/json;charset=UTF-8", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void deleteTelegramCity ( @PathVariable Long id, @Valid @RequestBody TelegramCity request ){
        telegramCityService.deleteTelegramCity (id);
    }
}
