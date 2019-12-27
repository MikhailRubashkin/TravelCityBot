package com.example.travelcitybot.repos;

import com.example.travelcitybot.domain.TelegramCity;
        import org.springframework.data.repository.CrudRepository;

public interface CityRepo extends CrudRepository<TelegramCity, Long> {

    //List<TelegramCity> findByCity( String city);
}
