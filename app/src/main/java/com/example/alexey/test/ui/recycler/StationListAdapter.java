package com.example.alexey.test.ui.recycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alexey.test.R;
import com.example.alexey.test.model.entity.StationEntity;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

/**
 * Created by alexey on 23.07.18.
 */

public class StationListAdapter extends RecyclerView.Adapter<StationViewHolder>
        implements StickyRecyclerHeadersAdapter<CityHeaderViewHolder> {

    private final Context context;
    private StickyRecycleData data;
    @Nullable
    private Listener listener;

    public StationListAdapter(Context context) {
        this.context = context;
    }

    public void setData(StickyRecycleData data) {
        this.data = data;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }
    @Override
    public long getHeaderId(int position) {
        return data.getStationEntities().get(position).getCityId();
    }

    @Override
    public CityHeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_city_header, parent, false);
        return new CityHeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(CityHeaderViewHolder holder, int position) {
        holder.setData(data.getHeaders().get(getHeaderId(position)));
    }

    @NonNull
    @Override
    public StationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_station_item, parent, false);
        StationViewHolder stationViewHolder = new StationViewHolder(view);
        view.setOnClickListener(view1 -> {
            if (listener != null)
                listener.onItemClick(stationViewHolder.getData());
        });
        view.findViewById(R.id.detail_icon).setOnClickListener(view1 -> {
            if (listener != null)
                listener.onDetailItem(stationViewHolder.getData());
        });
        return stationViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StationViewHolder holder, int position) {
        holder.setData(data.getStationEntities().get(position));
    }

    @Override
    public int getItemCount() {
        return data != null ? data.getStationEntities().size() : 0;
    }

    public interface Listener {
        void onItemClick(StationEntity stationEntity);
        void onDetailItem(StationEntity stationEntity);
    }
}
