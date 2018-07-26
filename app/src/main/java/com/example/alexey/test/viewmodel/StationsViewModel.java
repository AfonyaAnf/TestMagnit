package com.example.alexey.test.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.alexey.test.TestApplication;
import com.example.alexey.test.model.entity.CityEntity;
import com.example.alexey.test.model.entity.StationEntity;
import com.example.alexey.test.model.StationsRepository;
import com.example.alexey.test.model.TypeStations;
import com.example.alexey.test.ui.recycler.StickyRecycleData;

import java.util.List;

/**
 * Created by alexey on 23.07.18.
 */

public class StationsViewModel extends ViewModel {

    private MutableLiveData<Boolean> progressVisibilityLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> contentVisibilityLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> emptyDataVisibilityLiveData = new MutableLiveData<>();
    private MutableLiveData<StickyRecycleData> stickyRecycleLiveData = new MutableLiveData<>();

    private StationsRepository repository;
    private TypeStations typeStations;
    private StickyRecycleData recycleData;

    public StationsViewModel() {
        recycleData = new StickyRecycleData();
        repository = new StationsRepository(TestApplication.getInstance().getDatabase());
    }

    public LiveData<StickyRecycleData> getRecyclerLiveData() {
        return stickyRecycleLiveData;
    }

    public LiveData<Boolean> getProgressVisibilityLiveData() {
        return progressVisibilityLiveData;
    }

    public LiveData<Boolean> getContentVisibilityLiveData() {
        return contentVisibilityLiveData;
    }

    public LiveData<Boolean> getEmptyDataVisibilityLiveData() {
        return emptyDataVisibilityLiveData;
    }

    public void init(TypeStations typeStations) {
        this.typeStations = typeStations;
        contentVisibilityLiveData.setValue(false);
        emptyDataVisibilityLiveData.setValue(false);
        progressVisibilityLiveData.setValue(true);
        update("");
    }

    public void update(String search) {
        repository.getStations(typeStations, search).observeForever(this::observeGetStations);
        repository.getCities().observeForever(this::observeGetCities);
    }
    private void observeGetCities(List<CityEntity> cities) {
        recycleData.setHeaders(CityEntity.getFromCitiesList(cities));
        onDataLoaded();
    }

    private void observeGetStations(List<StationEntity> stations) {
        recycleData.setStationEntities(stations);
        onDataLoaded();
    }

    private void onDataLoaded() {
        if (recycleData.getHeaders() != null && recycleData.getStationEntities() != null) {
            stickyRecycleLiveData.setValue(recycleData);
            if (recycleData.getStationEntities().size() == 0) {
                emptyDataVisibilityLiveData.setValue(true);
                contentVisibilityLiveData.setValue(false);
            } else {
                contentVisibilityLiveData.setValue(true);
                emptyDataVisibilityLiveData.setValue(false);
            }
            progressVisibilityLiveData.setValue(false);
        }
    }

}
