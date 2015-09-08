package com.pioneer.aaron.fitness.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.pioneer.aaron.fitness.R;

/**
 * Created by Aaron on 9/8/15.
 */
public class TrackDialogFragment extends DialogFragment {
    View rootView;
    int res_id;
    public static TrackDialogFragment newInstance(int res_id) {
        TrackDialogFragment trackDialogFragment = new TrackDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("res_id", res_id);
        trackDialogFragment.setArguments(bundle);
        return trackDialogFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.track_dialog_layout, container, false);

        initFragment();
        return rootView;
    }

    private void initFragment() {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        Bundle bundle = getArguments();
        res_id = bundle.getInt("res_id");
        ((ImageView)rootView.findViewById(R.id.track_thumbnail)).setImageResource(res_id);
    }
}
