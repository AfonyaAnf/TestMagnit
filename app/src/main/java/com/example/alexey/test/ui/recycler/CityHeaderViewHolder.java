package com.example.alexey.test.ui.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.alexey.test.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alexey on 23.07.18.
 */

public class CityHeaderViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.text)
    TextView textView;

    public CityHeaderViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setData(String data) {
        textView.setText(data);
    }
}
