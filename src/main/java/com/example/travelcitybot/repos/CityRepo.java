package com.example.travelcitybot.repos;

import com.example.travelcitybot.domain.TelegramCity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CityRepo  extends CrudRepository<TelegramCity, Long> {

    List<TelegramCity> findByTelegramCity( String telegramCity);
}
