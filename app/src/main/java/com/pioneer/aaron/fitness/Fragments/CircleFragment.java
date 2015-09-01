package com.pioneer.aaron.fitness.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pioneer.aaron.fitness.R;

/**
 * Created by Aaron on 8/27/15.
 */
public class CircleFragment extends Fragment {

    public static CircleFragment newInstance() {
        return new CircleFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.circle_fragment_layout, container, false);
        return rootView;
    }

}
