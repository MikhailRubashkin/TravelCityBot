package com.example.travelcitybot.service;

import com.example.travelcitybot.domain.TelegramCity;
import com.example.travelcitybot.repos.CityRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TelegramCityService {

    private final CityRepo cityRepo;

    public TelegramCityService ( CityRepo cityRepo ){
        this.cityRepo = cityRepo;
    }

    @Transactional
    public List<TelegramCity> findByTelegramCity ( String telegramCity ){
        return cityRepo.findByTelegramCity (telegramCity);
    }

    @Transactional
    public List<TelegramCity> findAllTelegramCity (){
        return (List<TelegramCity>) cityRepo.findAll ( );
    }

    @Transactional
    public Optional<TelegramCity> findById ( long id ){
        return cityRepo.findById (id);
    }

    @Transactional
    public void addTelegramCity ( TelegramCity telegramCity ){
        cityRepo.save (telegramCity);
    }

    @Transactional
    public void updateTelegramCity ( TelegramCity telegramCity ){
        cityRepo.save (telegramCity);
    }

    @Transactional
    public void deleteTelegramCity ( Long id ){
        cityRepo.deleteById (id);
    }
}
