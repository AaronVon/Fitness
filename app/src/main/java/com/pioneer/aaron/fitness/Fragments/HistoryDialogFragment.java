package com.pioneer.aaron.fitness.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.pioneer.aaron.fitness.Utils.Constant;
import com.pioneer.aaron.fitness.R;

/**
 * Created by Aaron on 8/31/15.
 */
public class HistoryDialogFragment extends DialogFragment {

    private View rootView;
    private int year, month, day;

    public static HistoryDialogFragment newInstance(int year, int month, int day) {
        HistoryDialogFragment historyDialogFragment = new HistoryDialogFragment();
        Bundle mBundle = new Bundle();
        mBundle.putInt(Constant.HIS_YEAR, year);
        mBundle.putInt(Constant.HIS_MONTH, month);
        mBundle.putInt(Constant.HIS_DAY, day);

        historyDialogFragment.setArguments(mBundle);
        return historyDialogFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.history_dialog_fragment_layout, container, false);

        initFragment();
        return rootView;
    }

    private void initFragment() {
        //remove the default header
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        Bundle mBundle = getArguments();
        this.year = mBundle.getInt(Constant.HIS_YEAR);
        this.month = mBundle.getInt(Constant.HIS_MONTH);
        this.day = mBundle.getInt(Constant.HIS_DAY);


    }
}
