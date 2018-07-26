package com.example.alexey.test.model.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.example.alexey.test.db.DataTypeConverter;
import com.example.alexey.test.model.TypeStations;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexey on 19.07.18.
 */

@Entity(tableName = "station")
public class StationEntity implements Serializable {

    /**
     * Получение списка станций из полученной структуры (json)
     * @param cityEntities список городов со станциями
     * @return список станций
     */
    public static List<StationEntity> getStations(List<CityEntity> cityEntities) {
        List<StationEntity> stationEntities = new ArrayList<>();
        for (CityEntity cityEntity : cityEntities) {
            stationEntities.addAll(cityEntity.getStations());
        }
        return stationEntities;
    }

    public StationEntity() {
    }

    private String countryTitle;
    private String districtTitle;
    private long cityId;
    private String cityTitle;
    private String regionTitle;
    private String stationTitle;
    private boolean isFrom;
    private boolean isTo;
    @PrimaryKey
    private int stationId;
    @TypeConverters(DataTypeConverter.class)
    private GeoPointEntity point;


    public boolean isFrom() {
        return isFrom;
    }

    public void setFrom(boolean from) {
        isFrom = from;
    }

    public boolean isTo() {
        return isTo;
    }

    public void setTo(boolean to) {
        isTo = to;
    }

    public String getCountryTitle() {
        return countryTitle;
    }

    public void setCountryTitle(String countryTitle) {
        this.countryTitle = countryTitle;
    }

    public String getDistrictTitle() {
        return districtTitle;
    }

    public void setDistrictTitle(String districtTitle) {
        this.districtTitle = districtTitle;
    }

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public String getCityTitle() {
        return cityTitle;
    }

    public void setCityTitle(String cityTitle) {
        this.cityTitle = cityTitle;
    }

    public String getRegionTitle() {
        return regionTitle;
    }

    public void setRegionTitle(String regionTitle) {
        this.regionTitle = regionTitle;
    }

    public String getStationTitle() {
        return stationTitle;
    }

    public void setStationTitle(String stationTitle) {
        this.stationTitle = stationTitle;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public GeoPointEntity getPoint() {
        return point;
    }

    public void setPoint(GeoPointEntity point) {
        this.point = point;
    }
}
