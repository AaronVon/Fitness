package com.pioneer.aaron.fitness.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.TextView;

import com.baoyz.widget.PullRefreshLayout;
import com.pioneer.aaron.fitness.Activities.PlaygroundDetails;
import com.pioneer.aaron.fitness.Adapters.PlaygroundItemAdapter;
import com.pioneer.aaron.fitness.Adapters.PlaygroundItemModel;
import com.pioneer.aaron.fitness.Interface.RecyclerViewItemClickListener;
import com.pioneer.aaron.fitness.R;
import com.pioneer.aaron.fitness.Utils.Constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Aaron on 9/6/15.
 */
public class PlaygroundFragment extends Fragment {

    private View rootView;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    private RecyclerView recyclerView;
    private PullRefreshLayout refreshLayout;
    private List<PlaygroundItemModel> dataSet;

    public static PlaygroundFragment newInstance() {
        return new PlaygroundFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.playground_layout, container, false);
        initFragment();
        initView();
        return rootView;
    }

    private void initView() {
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
        PlaygroundItemAdapter adapter = new PlaygroundItemAdapter(dataSet);
        adapter.setOnItemCLickListener(new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                PlaygroundItemModel data = dataSet.get(position);
                if (data != null) {
                    Intent intent = new Intent(getContext(), PlaygroundDetails.class);
                    Bundle bundle = new Bundle();
                    bundle.putString(Constant.PLAYGROUND_ID, data.playground_id);
                    bundle.putString(Constant.PLAYGROUND_LOCATION, data.playground_location);
                    bundle.putString(Constant.PLAYGROUND_TYPE, brief[position][0]);
                    bundle.putString(Constant.PLAYGROUND_MA, brief[position][1]);
                    bundle.putString(Constant.PLAYGROUND_TRACK, brief[position][2]);
                    bundle.putString(Constant.PLAYGROUND_BH, brief[position][3]);
                    bundle.putString(Constant.PLAYGROUND_PRICE, brief[position][4]);
                    bundle.putString(Constant.PLAYGROUND_BRIEF, brief[position][5]);

                    bundle.putInt(Constant.PLAYGROUND_THUMBNAIL, data.playground_thumbnail);
                    bundle.putBooleanArray(Constant.PLAYGROUND_BOOLEANS, data.tag_Booleans);

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
        boolean[] tag_boolean_1 = {true, true, true, false, false, false, true},
                tag_Booleans_2 = {false, false, true, false, false, false, false},
                tag_Booleans_3 = {true, true, false, false, false, false, true},
                tag_Booleans_4 = {false, false, false, false, true, false, false, false},
                tag_Booleans_5 = {false, false, false, false, false, false, true},
                tag_Booleans_6 = {true, true, false, false, false, true, true},
                tag_Booleans_7 = {false, false, false, true, true, false, false};

        dataSet.add(new PlaygroundItemModel(R.drawable.lzu, "兰州大学", tag_boolean_1, "天水南路22号"));
        dataSet.add(new PlaygroundItemModel(R.drawable.lanhaiyouyongguan, "兰海游泳馆", tag_Booleans_2, "甘南东路171号"));
        dataSet.add(new PlaygroundItemModel(R.drawable.zhongyiyaodaxue, "甘肃中医药大学", tag_Booleans_3, "定西路35号"));
        dataSet.add(new PlaygroundItemModel(R.drawable.jiayiyujia, "佳怡瑜伽", tag_Booleans_4, "阳光雅居"));
        dataSet.add(new PlaygroundItemModel(R.drawable.lanzhoutiyuguan, "兰州体育馆", tag_Booleans_5, "广场南路129号"));
        dataSet.add(new PlaygroundItemModel(R.drawable.tiyugongyuan, "体育公园", tag_Booleans_6, "平凉路"));
        dataSet.add(new PlaygroundItemModel(R.drawable.yizhaoweide, "一兆韦德健身俱乐部", tag_Booleans_7, "金阊北路75号"));
    }

    private void initFragment() {
        getActivity().setTitle(R.string.playground);
        mToolbar = (Toolbar) rootView.findViewById(R.id.tool_bar);
        mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerToggle.syncState();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_playground, menu);

        menu.findItem(R.id.menu_location);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.menu.menu_playground:
                break;
        }
        return true;
    }

    private String[][] brief = {{"outdoor", "plastic", "circle", "all day", "free", "兰州大学创建于1909年，其前身是清末新政期间设立的甘肃法政学堂，开启了西北高等教育的先河。其田径场免费对市民开放，是跑步的好场所"},
            {"indoor", "water", "1.4-1.8m", "7:00-20:30", "￥2600/year\n￥30/time", "兰海游泳馆隶属于甘肃兰海商贸责任公司，集游泳健身，悠闲娱乐为一体"},
            {"school", "mixed", "circle", "all day", "free", "甘肃中医药大学是2015年经教育部批准由成立于1978年的甘肃中医学院更名而成，坐落在甘肃省省会兰州市。学校为社会培养输送了3万余名合格的中医药及相关专业人才，为甘肃医疗卫生事业和经济社会发展做出了应有的贡献。"},
            {"indoor", "yoga", "coach provided", "10:00-20:00", "22/time", "瑜伽姿势运用古老而易于掌握的技巧，改善人们生理、心理、情感和精神方面的能力，是一种达到身体、心灵与精神和谐统一的运动方式，包括调身的体位法、调息的呼吸法、调心的冥想法等，以达至身心的合一。"},
            {"indoor", "mixed", "wood", "8:00-20:00", "20/h", "新建的大型保龄球馆，设备一流、环境舒适，与原有的健身馆、羽毛球馆、乒乓球馆、篮球馆、台球厅等构成了兰州地段最佳的全民健身场所。"},
            {"outdoor", "mixed", "circle", "all day", "free", "体育公园是主题公园的一种类型，园内把体育健身场地和生态园林环境巧妙地融为一体，是体育锻炼、健身休闲型的公共场所！"},
            {"indoor", "floor", "mix", "7:00-20:00", "￥3000/year", "新开的健身房，走高端路线，环境很好，附有教练"}};
}
