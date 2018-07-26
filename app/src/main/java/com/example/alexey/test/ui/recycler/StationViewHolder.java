package com.example.alexey.test.ui.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.alexey.test.R;
import com.example.alexey.test.model.entity.StationEntity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alexey on 23.07.18.
 */

public class StationViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.text)
    TextView textView;
    private StationEntity data;

    public StationViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setData(StationEntity data) {
        this.data = data;
        textView.setText(data.getStationTitle());
    }

    public StationEntity getData() {
        return data;
    }
}
