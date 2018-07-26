package com.example.alexey.test.model;

import com.example.alexey.test.model.entity.CityEntity;

import java.util.List;

/**
 * Created by alexey on 19.07.18.
 */

public class StationsResponse {

    private List<CityEntity> citiesFrom;

    private List<CityEntity> citiesTo;

    public List<CityEntity> getCitiesFrom() {
        return citiesFrom;
    }

    public void setCitiesFrom(List<CityEntity> citiesFrom) {
        this.citiesFrom = citiesFrom;
    }

    public List<CityEntity> getCitiesTo() {
        return citiesTo;
    }

    public void setCitiesTo(List<CityEntity> citiesTo) {
        this.citiesTo = citiesTo;
    }
}
