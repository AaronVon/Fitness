package com.pioneer.aaron.fitness.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baoyz.widget.PullRefreshLayout;
import com.pioneer.aaron.fitness.Adapters.CircleItemAdapter;
import com.pioneer.aaron.fitness.Adapters.CircleItemModel;
import com.pioneer.aaron.fitness.Interface.RecyclerViewItemClickListener;
import com.pioneer.aaron.fitness.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aaron on 8/27/15.
 */
public class CircleFragment extends Fragment {

    private View rootView;
    private RecyclerView recyclerView;
    private PullRefreshLayout refreshLayout;
    private List<CircleItemModel> dataSet;

    public static CircleFragment newInstance() {
        return new CircleFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.circle_fragment_layout, container, false);

        initFragment();
        return rootView;
    }

    private void initFragment() {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        //this line is a must to display recyclerview
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        refreshLayout = (PullRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);
        refreshLayout.setRefreshStyle(PullRefreshLayout.STYLE_WATER_DROP);
        refreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });
        initDataSet();
        CircleItemAdapter adapter = new CircleItemAdapter(dataSet);
        adapter.setOnItemClickListener(new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                CircleItemModel model = dataSet.get(position);
                if (model != null) {
                    Snackbar.make(rootView.findViewById(R.id.swipeRefreshLayout), position + "", Snackbar.LENGTH_SHORT)
                            .show();
                }
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void initDataSet() {
        dataSet = new ArrayList<>();
        dataSet.add(new CircleItemModel(R.drawable.user_1, "Jason", true, true, false));
        dataSet.add(new CircleItemModel(R.drawable.user_2, "Mark", false, true, false));
        dataSet.add(new CircleItemModel(R.drawable.user_3, "Chris", true, true, true));
        dataSet.add(new CircleItemModel(R.drawable.user_4, "Eric", true, false, false));
        dataSet.add(new CircleItemModel(R.drawable.user_5, "Alex", true, true, true));
        dataSet.add(new CircleItemModel(R.drawable.user_6, "Joseph", true, false, true));
    }



}
