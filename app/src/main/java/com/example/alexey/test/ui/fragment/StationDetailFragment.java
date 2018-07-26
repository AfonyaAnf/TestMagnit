package com.example.alexey.test.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.alexey.test.R;
import com.example.alexey.test.model.entity.StationEntity;

import butterknife.BindView;

/**
 * Created by alexey on 26.07.18.
 */

public class StationDetailFragment extends BaseFragment {

    public static final String STATION_ARG = "station_arg";

    @BindView(R.id.address)
    TextView addressTextView;
    @BindView(R.id.city)
    TextView cityTextView;
    @BindView(R.id.region)
    TextView regionTextView;
    @BindView(R.id.country)
    TextView countryTextView;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            StationEntity station = (StationEntity) getArguments().getSerializable(STATION_ARG);
            if(station == null) return;
            addressTextView.setText(String.format("%s: %s", getString(R.string.address), station.getStationTitle()));
            cityTextView.setText(String.format("%s: %s", getString(R.string.city), station.getCityTitle()));
            regionTextView.setText(String.format("%s: %s", getString(R.string.region), station.getRegionTitle()));
            countryTextView.setText(String.format("%s: %s", getString(R.string.country), station.getCountryTitle()));
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_station_detail;
    }
}
