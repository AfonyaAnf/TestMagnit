package com.example.alexey.test.ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.alexey.test.R;
import com.example.alexey.test.Utils;
import com.example.alexey.test.model.entity.StationEntity;
import com.example.alexey.test.model.TypeStations;
import com.example.alexey.test.ui.recycler.StationListAdapter;
import com.example.alexey.test.ui.recycler.StickyRecycleData;
import com.example.alexey.test.viewmodel.StationsViewModel;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import butterknife.BindView;

/**
 * Created by alexey on 19.07.18.
 */

public class StationsFragment extends BaseFragment implements StationListAdapter.Listener {

    public static String IS_STATION_FROM_ARG = "is_station_from_arg";
    public static String IS_STATION_TO_ARG = "is_station_to_arg";

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.empty_data)
    TextView emptyTextView;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private StationListAdapter stationListAdapter;
    private StationsViewModel stationsViewModel;
    @Nullable
    private FragmentCallback<StationEntity> fragmentCallback;

    public void setFragmentCallback(FragmentCallback<StationEntity> fragmentCallback) {
        this.fragmentCallback = fragmentCallback;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        stationsViewModel = ViewModelProviders.of(this).get(StationsViewModel.class);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        stationListAdapter = new StationListAdapter(getContext());
        stationListAdapter.setListener(this);
        recyclerView.setAdapter(stationListAdapter);
        recyclerView.addItemDecoration(new StickyRecyclerHeadersDecoration(stationListAdapter));

        stationsViewModel.getEmptyDataVisibilityLiveData().observe(this, this::onEmptyDataVisibility);
        stationsViewModel.getProgressVisibilityLiveData().observe(this, this::onProgressVisibility);
        stationsViewModel.getContentVisibilityLiveData().observe(this, this::onContentVisibility);
        stationsViewModel.getRecyclerLiveData().observe(this, this::onRecyclerData);
        if (getArguments() != null) {
            if (getArguments().getBoolean(IS_STATION_FROM_ARG, false)) {
                stationsViewModel.init(TypeStations.FROM);
            } else if (getArguments().getBoolean(IS_STATION_TO_ARG, false)) {
                stationsViewModel.init(TypeStations.TO);
            }
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.stations, menu);
        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                stationsViewModel.update(newText);
                return true;
            }
        });
        searchView.clearFocus();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_stations;
    }

    private void onProgressVisibility(@Nullable Boolean visible) {
        if (visible == null)
            return;
        progressBar.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    private void onContentVisibility(@Nullable Boolean visible) {
        if (visible == null)
            return;
        recyclerView.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    private void onEmptyDataVisibility(@Nullable Boolean visible) {
        if (visible == null)
            return;
        emptyTextView.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    private void onRecyclerData(@Nullable StickyRecycleData stationEntities) {
        if (stationEntities == null)
            return;
        stationListAdapter.setData(stationEntities);
        stationListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(StationEntity stationEntity) {
        if (getFragmentManager() != null)
            getFragmentManager().popBackStack();
        if (fragmentCallback != null)
            fragmentCallback.setData(stationEntity);
    }

    @Override
    public void onDetailItem(StationEntity stationEntity) {
        Fragment fragment = new StationDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(StationDetailFragment.STATION_ARG, stationEntity);
        fragment.setArguments(bundle);
        Utils.openFragment(getFragmentManager(), fragment);
    }
}
