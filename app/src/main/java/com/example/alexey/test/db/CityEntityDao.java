package com.example.alexey.test.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.alexey.test.model.entity.CityEntity;

import java.util.List;

/**
 * Created by alexey on 23.07.18.
 */

@Dao
public interface CityEntityDao {

    @Query("SELECT * FROM city ORDER BY cityId")
    LiveData<List<CityEntity>> getCitiesName();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<CityEntity> city);

    @Query("DELETE FROM station")
    void deleteAll();
}
