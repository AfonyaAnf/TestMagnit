package com.example.alexey.test.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.alexey.test.model.entity.StationEntity;

import java.util.List;

/**
 * Created by alexey on 23.07.18.
 */

@Dao
public interface StationEntityDao {

    @Query("SELECT * FROM station")
    LiveData<List<StationEntity>> getAll();

    @Query("SELECT * FROM station WHERE isFrom = 1 AND stationTitle LIKE :search ORDER BY cityId")
    LiveData<List<StationEntity>> getFromStations(String search);

    @Query("SELECT * FROM station WHERE isTo = 1 AND stationTitle LIKE :search ORDER BY cityId")
    LiveData<List<StationEntity>> getToStations(String search);

    @Query("SELECT * FROM station WHERE stationId = :id")
    LiveData<StationEntity> getById(long id);

    @Query("SELECT Count(*) FROM station")
    int getCountRows();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    List<Long> insertAll(List<StationEntity> city);

    @Query("UPDATE station SET isFrom = 1 WHERE stationId = :stationId;")
    void setStationFromFlag(long stationId);

    @Query("UPDATE station SET isTo = 1 WHERE stationId = :stationId;")
    void setStationToFlag(long stationId);

    @Query("DELETE FROM station")
    void deleteAll();
}
