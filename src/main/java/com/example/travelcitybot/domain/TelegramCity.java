package com.example.travelcitybot.domain;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table (name = "TelegramCity")
public class TelegramCity implements Serializable {

    private static final long serialVersionUID = 1234567895757575L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "telegramCity", unique = true, nullable = false)
    private String telegramCity;
    @Column(name = "descriptionCity", unique = true, nullable = false)
    private String descriptionCity;

    public TelegramCity (){
    }

    public TelegramCity ( String telegramCity, String descriptionCity ){
        this.telegramCity = telegramCity;
        this.descriptionCity = descriptionCity;
    }

    public TelegramCity ( String telegramCity ){
        this.telegramCity = telegramCity;
    }

    public long getId (){
        return id;
    }

    public void setId ( long id ){
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

        if (id != that.id) return false;
        if (!telegramCity.equals (that.telegramCity)) return false;
        return descriptionCity.equals (that.descriptionCity);
    }

    @Override
    public int hashCode (){
        int result = (int) (id ^ (id >>> 32));
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
