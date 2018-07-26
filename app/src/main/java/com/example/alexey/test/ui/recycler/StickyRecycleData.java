package com.example.alexey.test.ui.recycler;

/**
 * Created by alexey on 26.07.18.
 */


import android.support.v4.util.LongSparseArray;

import com.example.alexey.test.model.entity.StationEntity;

import java.util.List;

/**
 * Впомогательный класс инкапсулирующий данные, необходимые для адаптера StickyRecyclerView
 */
public class StickyRecycleData {
    private List<StationEntity> stationEntities;
    private LongSparseArray<String> headers;

    public List<StationEntity> getStationEntities() {
        return stationEntities;
    }

    public void setStationEntities(List<StationEntity> stationEntities) {
        this.stationEntities = stationEntities;
    }

    public LongSparseArray<String> getHeaders() {
        return headers;
    }

    public void setHeaders(LongSparseArray<String> headers) {
        this.headers = headers;
    }
}
