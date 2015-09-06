package com.pioneer.aaron.fitness.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.baoyz.widget.PullRefreshLayout;
import com.pioneer.aaron.fitness.Adapters.PlaygroundItemModel;
import com.pioneer.aaron.fitness.R;

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
}
