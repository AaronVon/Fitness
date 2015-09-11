package com.pioneer.aaron.fitness.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baoyz.widget.PullRefreshLayout;
import com.pioneer.aaron.fitness.Adapters.GamesItemAdapter;
import com.pioneer.aaron.fitness.Adapters.GamesItemModel;
import com.pioneer.aaron.fitness.Interface.RecyclerViewItemClickListener;
import com.pioneer.aaron.fitness.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aaron on 9/6/15.
 */
public class GamesFragment extends Fragment {
    private View rootView;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private RecyclerView recyclerView;
    private PullRefreshLayout refreshLayout;
    private List<GamesItemModel> dataSet;
    public static GamesFragment newInstance() {
        return new GamesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.games_layout, container, false);
        initFragment();
        return rootView;
    }

    private void initFragment() {
        getActivity().setTitle(R.string.games);
        mToolbar = (Toolbar) rootView.findViewById(R.id.tool_bar);
        //invoke the DrawerLayout from getActivity
        mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);

        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerToggle.syncState();

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
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
        GamesItemAdapter adapter = new GamesItemAdapter(dataSet);
        adapter.setOnItemClickListener(new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
        recyclerView.setAdapter(adapter);

    }

    private void initDataSet() {
        dataSet = new ArrayList<>();
        dataSet.add(new GamesItemModel(R.drawable.game_1, "镇宁黄果树国际马拉松", "5402", "8天"));
        dataSet.add(new GamesItemModel(R.drawable.game_2, "渣打银行曼谷马拉松", "2001", "20天"));
        dataSet.add(new GamesItemModel(R.drawable.game_3, "河南尧山超级马拉松", "344", "4天"));
        dataSet.add(new GamesItemModel(R.drawable.game_4, "温江半程马拉松", "439", "3天"));
        dataSet.add(new GamesItemModel(R.drawable.game_5, "渣打银行新加坡马拉松", "245", "结束"));
        dataSet.add(new GamesItemModel(R.drawable.game_6, "太原国际马拉松", "6324", "2天"));
        dataSet.add(new GamesItemModel(R.drawable.game_7, "大连长山群岛马拉松", "73342", "结束"));
        dataSet.add(new GamesItemModel(R.drawable.game_8, "六盘水夏季国际马拉松", "87602", "结束"));
        dataSet.add(new GamesItemModel(R.drawable.game_9, "贵阳国际马拉松", "63375", "结束"));
        dataSet.add(new GamesItemModel(R.drawable.game_10, "兰州国际马拉松", "64212", "结束"));
        dataSet.add(new GamesItemModel(R.drawable.game_11, "秦皇岛国际马拉松", "51909", "结束"));
        dataSet.add(new GamesItemModel(R.drawable.game_12, "都江堰双遗马拉松", "26232", "结束"));

    }
}
