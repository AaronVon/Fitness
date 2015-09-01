package com.pioneer.aaron.fitness.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;

import com.pioneer.aaron.fitness.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aaron on 8/27/15.
 */
public class MainFragment extends Fragment {

    private View rootView;
    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ViewPager mViewPager;
    private DrawerLayout mDrawerLayout;
    private List<Fragment> fragments;
    private List<String> titles;


    public static MainFragment newInstance() {
        return new MainFragment();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.main_fragment_layout, container, false);
        getActivity().setTitle(R.string.homepage);
        initFragment();

        return rootView;
    }

    private void initFragment() {
        mToolbar = (Toolbar) rootView.findViewById(R.id.tool_bar);
        mTabLayout = (TabLayout) rootView.findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) rootView.findViewById(R.id.view_pager);
        mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);

        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        mDrawerToggle.setDrawerIndicatorEnabled(true);

        titles = new ArrayList<>();
        titles.add(getString(R.string.activity));
        titles.add(getString(R.string.circle));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(1)));

        fragments = new ArrayList<>();
        fragments.add(ActivityFragment.newInstance());
        fragments.add(CircleFragment.newInstance());

        FragmentAdapter fragmentAdapter = new FragmentAdapter(getChildFragmentManager(), fragments, titles);
        mViewPager.setAdapter(fragmentAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(fragmentAdapter);
    }
}
