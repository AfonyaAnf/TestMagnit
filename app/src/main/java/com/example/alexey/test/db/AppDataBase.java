package com.example.alexey.test.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.alexey.test.model.entity.CityEntity;
import com.example.alexey.test.model.entity.StationEntity;

/**
 * Created by alexey on 23.07.18.
 */

@Database(entities = {CityEntity.class, StationEntity.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract StationEntityDao getStationDao();
    public abstract CityEntityDao getCityDao();
}
