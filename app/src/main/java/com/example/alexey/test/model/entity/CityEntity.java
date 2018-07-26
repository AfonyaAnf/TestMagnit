package com.example.alexey.test.model.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.v4.util.LongSparseArray;

import com.example.alexey.test.db.DataTypeConverter;

import java.util.List;

/**
 * Created by alexey on 19.07.18.
 */

@Entity(tableName = "city")
public class CityEntity {

    public static LongSparseArray<String> getFromCitiesList(List<CityEntity> cityEntities) {
        LongSparseArray<String> longSparseArray = new LongSparseArray<>();
        for (CityEntity cityEntity : cityEntities) {
            longSparseArray.put(cityEntity.cityId, String.format("%s, %s", cityEntity.countryTitle, cityEntity.cityTitle));
        }
        return longSparseArray;
    }

    private String countryTitle;
    private String districtTitle;
    @PrimaryKey
    private long cityId;
    private String cityTitle;
    private String regionTitle;
    @TypeConverters(DataTypeConverter.class)
    private GeoPointEntity point;
    @Ignore
    private List<StationEntity> stations;

    public CityEntity() {
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

    public GeoPointEntity getPoint() {
        return point;
    }

    public void setPoint(GeoPointEntity point) {
        this.point = point;
    }

    public List<StationEntity> getStations() {
        return stations;
    }

    public void setStations(List<StationEntity> stations) {
        this.stations = stations;
    }
}
