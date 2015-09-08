package com.pioneer.aaron.fitness.Fragments;

import android.content.Intent;
import android.graphics.drawable.Animatable;
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
import com.pioneer.aaron.fitness.Activities.CircleDetails;
import com.pioneer.aaron.fitness.Adapters.CircleItemAdapter;
import com.pioneer.aaron.fitness.Adapters.CircleItemModel;
import com.pioneer.aaron.fitness.Interface.RecyclerViewItemClickListener;
import com.pioneer.aaron.fitness.R;
import com.pioneer.aaron.fitness.Utils.Constant;

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
                CircleItemModel data = dataSet.get(position);
                if (data != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString(Constant.P_ID, data.user_id);
                    bundle.putInt(Constant.P_THUMBNAIL, data.user_thumbnail);
                    bundle.putString(Constant.P_SLOGAN, data.user_slogan);
                    bundle.putString(Constant.P_DISTANCE, details[position][0]);
                    bundle.putString(Constant.P_FANS, details[position][1]);
                    bundle.putString(Constant.P_ACHIEVE, details[position][2]);
                    Intent intent = new Intent(getContext(), CircleDetails.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.keep);
                }
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void initDataSet() {
        dataSet = new ArrayList<>();
        dataSet.add(new CircleItemModel(R.drawable.user_1, "大雨神", "坚持就可以", true, true, false));
        dataSet.add(new CircleItemModel(R.drawable.user_2, "233—兜兜", "you stop you die", false, true, false));
        dataSet.add(new CircleItemModel(R.drawable.user_3, "brave迪", "请叫我健身小达人", true, true, true));
        dataSet.add(new CircleItemModel(R.drawable.user_4, "617_Utr", "他啥也没说", true, false, false));
        dataSet.add(new CircleItemModel(R.drawable.user_5, "哈哈xu", "我要瘦到100", true, true, true));
        dataSet.add(new CircleItemModel(R.drawable.user_6, "追影子的", "keep moving", true, false, true));
    }

    private String[][] details = {
            {"500m", "24", "24.1km"},
            {"375m", "18", "44.2km"},
            {"100m", "10", "33.1km"},
            {"210m", "16", "39.8km"},
            {"105m", "43", "40.7km"},
            {"50m", "33", "29.0km"}};



}
