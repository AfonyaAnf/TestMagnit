package com.example.alexey.test.ui.fragment;

import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.alexey.test.R;
import com.example.alexey.test.Utils;
import com.example.alexey.test.ui.view.CustomTextView;
import com.example.alexey.test.viewmodel.TimetableViewModel;

import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by alexey on 19.07.18.
 */

public class TimetableFragment extends BaseFragment {

    @BindView(R.id.station_arrival)
    CustomTextView arrivalTextView;
    @BindView(R.id.station_departure)
    CustomTextView departureTextView;
    @BindView(R.id.date_arrival)
    CustomTextView customTextView;

    private TimetableViewModel timetableViewModel;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        timetableViewModel = ViewModelProviders.of(this).get(TimetableViewModel.class);
        timetableViewModel.getArrivalStationName().observe(this, this::onArrivalStationChange);
        timetableViewModel.getDepartureStationName().observe(this, this::onDepartureStationChanged);
        timetableViewModel.getDateLiveData().observe(this, this::onDateChange);
    }

    @OnClick(R.id.date_arrival)
    protected void onClickDate() {
        Calendar instance = Calendar.getInstance();
        new DatePickerDialog(getContext(), (view, year, monthOfYear, dayOfMonth) -> {
            timetableViewModel.setDate(dayOfMonth, year);
        }, instance.get(Calendar.YEAR),
                instance.get(Calendar.MONTH), instance.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    @OnClick(R.id.station_arrival)
    protected void onClickArrivalStation() {
        StationsFragment stationsFragment = new StationsFragment();
        stationsFragment.setFragmentCallback(timetableViewModel::setArrivalStation);
        Bundle bundle = new Bundle();
        bundle.putBoolean(StationsFragment.IS_STATION_FROM_ARG, true);
        stationsFragment.setArguments(bundle);
        Utils.openFragment(getFragmentManager(), stationsFragment);
    }

    @OnClick(R.id.station_departure)
    protected void onClickDepartureStation() {
        StationsFragment stationsFragment = new StationsFragment();
        stationsFragment.setFragmentCallback(timetableViewModel::setDepartureStation);
        Bundle bundle = new Bundle();
        bundle.putBoolean(StationsFragment.IS_STATION_TO_ARG, true);
        stationsFragment.setArguments(bundle);
        Utils.openFragment(getFragmentManager(), stationsFragment);
    }

    private void onArrivalStationChange(String s) {
        arrivalTextView.setData(s);
    }

    private void onDepartureStationChanged(String s) {
        departureTextView.setData(s);
    }

    private void onDateChange(String s) {
        customTextView.setData(s);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_choose_stations;
    }
}
