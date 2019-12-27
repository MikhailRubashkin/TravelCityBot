package com.example.travelcitybot.controller;


import com.example.travelcitybot.repos.CityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {

    @Autowired
    public CityRepo cityRepo;


}
