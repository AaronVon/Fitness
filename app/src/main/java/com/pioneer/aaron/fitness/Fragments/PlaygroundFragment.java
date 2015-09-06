package com.pioneer.aaron.fitness.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pioneer.aaron.fitness.R;

/**
 * Created by Aaron on 9/6/15.
 */
public class PlaygroundFragment extends Fragment {

    private View rootView;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    public static PlaygroundFragment newInstance() {
        return new PlaygroundFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.playground_layout, container, false);
        initFragment();
        return rootView;
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
}
