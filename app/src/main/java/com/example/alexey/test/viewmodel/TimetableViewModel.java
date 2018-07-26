package com.example.alexey.test.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.example.alexey.test.model.entity.StationEntity;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by alexey on 26.07.18.
 */

/**
 * Здесь храним состояние экрана выбранных станций,
 * если понадобится работать с объектами станций (н-р передача данных на бэк),
 * можно и их здесь хранить, пока только оповещаем об изменении названий станций
 */
public class TimetableViewModel extends ViewModel {

    private MutableLiveData<String> arrivalStationLiveData = new MutableLiveData<>();
    private MutableLiveData<String> departureStationLiveData = new MutableLiveData<>();
    private MutableLiveData<String> dateLiveData = new MutableLiveData<>();

    public MutableLiveData<String> getDateLiveData() {
        return dateLiveData;
    }

    public LiveData<String> getArrivalStationName() {
        return arrivalStationLiveData;
    }

    public LiveData<String> getDepartureStationName() {
        return departureStationLiveData;
    }

    public void setArrivalStation(@Nullable StationEntity arrivalStation) {
        if (arrivalStation != null)
            arrivalStationLiveData.setValue(arrivalStation.getStationTitle());
    }

    public void setDepartureStation(@Nullable StationEntity departureStation) {
        if (departureStation != null)
            departureStationLiveData.setValue(departureStation.getStationTitle());
    }

    public void setDate(int dayOfMonth, int year) {
        Calendar calendar = Calendar.getInstance();
        String displayName = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        getDateLiveData().setValue(String.format("%s %s %s", dayOfMonth, displayName, year));
    }


}
