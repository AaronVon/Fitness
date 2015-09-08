package com.pioneer.aaron.fitness.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
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
import com.pioneer.aaron.fitness.Utils.Constant;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateChangedListener;

/**
 * Created by Aaron on 8/27/15.
 */
public class HistoryFragment extends Fragment {

    private View rootView;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private MaterialCalendarView calendarView;

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.history_fragment_layout, container, false);
        getActivity().setTitle(R.string.history);

        initFragment();
        return rootView;
    }

    private void initFragment() {
        mToolbar = (Toolbar) rootView.findViewById(R.id.tool_bar);
        mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);

        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        mDrawerToggle.setDrawerIndicatorEnabled(true);

        //init CalendarView
        calendarView = (MaterialCalendarView) rootView.findViewById(R.id.calendarView);
        calendarView.setOnDateChangedListener(dateChangedListener);
    }

    private OnDateChangedListener dateChangedListener = new OnDateChangedListener() {
        @Override
        public void onDateChanged(MaterialCalendarView materialCalendarView, @Nullable CalendarDay calendarDay) {
            int year = calendarDay.getYear();
            int month = calendarDay.getMonth();
            int day = calendarDay.getDay();

            HistoryDialogFragment.newInstance(year, month, day)
                    .show((getActivity()).getSupportFragmentManager(), Constant.TAG_HIS_DIALOG);
        }
    };
}
