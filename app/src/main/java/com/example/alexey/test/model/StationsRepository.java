package com.example.alexey.test.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;

import com.example.alexey.test.TestApplication;
import com.example.alexey.test.Utils;
import com.example.alexey.test.db.AppDataBase;
import com.example.alexey.test.db.CityEntityDao;
import com.example.alexey.test.db.StationEntityDao;
import com.example.alexey.test.model.entity.CityEntity;
import com.example.alexey.test.model.entity.StationEntity;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by alexey on 23.07.18.
 * Класс для получения данных о станциях
 */

public class StationsRepository {

    private final StationEntityDao stationDao;
    private final CityEntityDao cityDao;

    public StationsRepository(AppDataBase dataBase) {
        this.stationDao = dataBase.getStationDao();
        this.cityDao = dataBase.getCityDao();
    }

    /**
     * @return liveData списка городов, сперва обновляя данные (если необходимо)
     */
    public LiveData<List<CityEntity>> getCities() {
        return Transformations.switchMap(updateIfNeeded(), input ->
                cityDao.getCitiesName());
    }

    /**
     * @param typeStations параметр типа искоых станций (from, to)
     * @return liveData списка станций, сперва обновляя данные (если необходимо)
     */

    public LiveData<List<StationEntity>> getStations(TypeStations typeStations, String search) {
        String formatSearch = "%" + search + "%";
        if (typeStations == TypeStations.FROM)
            return Transformations.switchMap(updateIfNeeded(), input ->
                    stationDao.getFromStations(formatSearch));

        if (typeStations == TypeStations.TO)
            return Transformations.switchMap(updateIfNeeded(), input ->
                    stationDao.getToStations(formatSearch));

        return Transformations.switchMap(updateIfNeeded(), input ->
                stationDao.getAll());
    }

    /**
     * Логика обновления данных
     * здесь можно поменять логику обновления данных, например обновлять не когда данных нет,
     * а с какой либо периодичностью (записывать дату последнего обновления и сверять
     * ее с текущей)
     * отдельный поток для получения данных из бд
     *
     * @return LiveData статуса обновления
     */

    private LiveData<Boolean> updateIfNeeded() {
        MutableLiveData<Boolean> booleanMutableLiveData = new MutableLiveData<>();
        new Thread(() -> {
            if (stationDao.getCountRows() == 0) {
                updateStations();
            }
            booleanMutableLiveData.postValue(true);
        }).start();
        return booleanMutableLiveData;
    }

    /**
     * Обновление данных о станциях в бд
     */
    private void updateStations() {
        String s = Utils.loadJSONFromAsset(TestApplication.getInstance().getBaseContext(), "allStations.json");
        StationsResponse stationsResponse = new Gson().fromJson(s, StationsResponse.class);

        // Обновляем данные городов
        cityDao.deleteAll();
        cityDao.insertAll(stationsResponse.getCitiesFrom());
        cityDao.insertAll(stationsResponse.getCitiesTo());

        // Извлекаем из полученной структуры станции
        List<StationEntity> stationsFrom = StationEntity.getStations(stationsResponse.getCitiesFrom());
        List<StationEntity> stationTo = StationEntity.getStations(stationsResponse.getCitiesTo());
        // Обновляем список станций
        stationDao.deleteAll();
        stationDao.insertAll(stationsFrom);
        stationDao.insertAll(stationTo);
        // Обновляем признаки типа станций (туда)
        for (StationEntity entity : stationsFrom) {
            stationDao.setStationFromFlag(entity.getStationId());
        }
        // Обновляем признаки типа станций (обратно)
        for (StationEntity entity : stationTo) {
            stationDao.setStationToFlag(entity.getStationId());
        }
    }
}
