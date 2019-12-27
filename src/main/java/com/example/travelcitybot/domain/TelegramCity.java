package com.example.travelcitybot.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class TelegramCity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String telegramCity;
    private String descriptionCity;

    public TelegramCity (){
    }

    public TelegramCity ( String telegramCity, String descriptionCity ){
        this.telegramCity = telegramCity;
        this.descriptionCity = descriptionCity;
    }

    public TelegramCity ( String city ){
        this.telegramCity = city;
    }

    public Integer getId (){
        return id;
    }

    public void setId ( Integer id ){
        this.id = id;
    }

    public String getTelegramCity (){
        return telegramCity;
    }

    public void setTelegramCity ( String telegramCity ){
        this.telegramCity = telegramCity;
    }

    public String getDescriptionCity (){
        return descriptionCity;
    }

    public void setDescriptionCity ( String descriptionCity ){
        this.descriptionCity = descriptionCity;
    }

    @Override
    public boolean equals ( Object o ){
        if (this == o) return true;
        if (o == null || getClass ( ) != o.getClass ( )) return false;

        TelegramCity that = (TelegramCity) o;

        if (!id.equals (that.id)) return false;
        if (!telegramCity.equals (that.telegramCity)) return false;
        return descriptionCity.equals (that.descriptionCity);
    }

    @Override
    public int hashCode (){
        int result = id.hashCode ( );
        result = 31 * result+telegramCity.hashCode ( );
        result = 31 * result+descriptionCity.hashCode ( );
        return result;
    }

    @Override
    public String toString (){
        return "TelegramCity{"+
               "id="+id+
               ", telegramCity='"+telegramCity+'\''+
               ", descriptionCity='"+descriptionCity+'\''+
               '}';
    }
}
