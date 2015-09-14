package com.pioneer.aaron.fitness.Fragments;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.IntentSender;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;


import com.dlazaro66.wheelindicatorview.WheelIndicatorItem;
import com.dlazaro66.wheelindicatorview.WheelIndicatorView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.HistoryApi;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.DataDeleteRequest;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.result.DataReadResult;
import com.google.android.gms.fitness.data.Field;
import com.pioneer.aaron.fitness.R;
import com.pioneer.aaron.fitness.Utils.Constant;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.Date;

/**
 * Created by Aaron on 8/27/15.
 */
public class ActivityFragment extends Fragment {

    private View rootView;
    private TextView workout_info;
    private int bpm_avg = 0, bpm_max = 0, bpm_min = 0,
            calories = 0,
            steps = 0,
            distance = 0;

    //fitness objects
    private GoogleApiClient mClient;
    private String TAG = "HistoryAPI";
    private boolean authInProgress = false;
    private static final int REQUEST_OAUTH = 1;
    private static final String AUTH_PENDING = "auth_state_pending";
    private static final String DATE_FORMAT = "yyyy.MM.dd HH:mm:ss";

    //WheelIndicatorView objects
    private WheelIndicatorView mWheelIndicatorView;
    private WheelIndicatorItem stepIndicatorItem;
    private WheelIndicatorItem calorieIndicatorItem;
    private WheelIndicatorItem distanceIndicatorItem;

    //SQLite db
    private SQLiteDatabase database;

    //animation
    Animation fadeinAnimation, fadeoutAnimation;

    public static ActivityFragment newInstance() {
        return new ActivityFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_framgent_layout, container, false);
        initFragment();
        return rootView;
    }

    private void initFragment() {
        fadeinAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        fadeinAnimation.setAnimationListener(animationListener);
        fadeoutAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);
        fadeoutAnimation.setAnimationListener(animationListener);
        workout_info = (TextView) rootView.findViewById(R.id.workout_info);
        workout_info.setOnClickListener(clickListener);

        mWheelIndicatorView = (WheelIndicatorView) rootView.findViewById(R.id.activity_indicator);
        stepIndicatorItem = new WheelIndicatorItem(steps, getResources().getColor(R.color.indicator_1));
        calorieIndicatorItem = new WheelIndicatorItem(calories, getResources().getColor(R.color.indicatior_2));
        distanceIndicatorItem = new WheelIndicatorItem(calories, getResources().getColor(R.color.indicator_3));

        mWheelIndicatorView.addWheelIndicatorItem(stepIndicatorItem);
        mWheelIndicatorView.addWheelIndicatorItem(distanceIndicatorItem);
        mWheelIndicatorView.addWheelIndicatorItem(calorieIndicatorItem);

        //init SQLite db
//        insertDataIntoDB();

        buildFitnessClient();
    }

    private Animation.AnimationListener animationListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };

    private void insertDataIntoDB() {
        database = getActivity().openOrCreateDatabase(Constant.DB_NAME, getActivity().MODE_PRIVATE, null);
        database.execSQL("DROP TABLE IF EXISTS historydata");
        database.execSQL("CREATE TABLE IF NOT EXISTS historydata " +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, bpm_avf INTEGER, calories INTEGER, distance INTEGER)");

    }

    private void buildFitnessClient() {
        //create Google API Client
        mClient = new GoogleApiClient.Builder(getActivity())
                .addApi(Fitness.HISTORY_API)
                .addScope(new Scope(Scopes.FITNESS_ACTIVITY_READ_WRITE))
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(Bundle bundle) {
                        Log.d(TAG, "connected!!!");
                        new InsertAndVerifyDataTask().execute();
                    }

                    @Override
                    public void onConnectionSuspended(int i) {
                        if (i == GoogleApiClient.ConnectionCallbacks.CAUSE_NETWORK_LOST) {
                            Log.d(TAG, "Connection lost.  Cause: Network Lost.");
                        }
                        if (i == GoogleApiClient.ConnectionCallbacks.CAUSE_SERVICE_DISCONNECTED) {
                            Log.d(TAG, "Connection lost.  Reason: Service Disconnected");
                        }
                    }
                })
                .addOnConnectionFailedListener(
                        new GoogleApiClient.OnConnectionFailedListener() {
                            @Override
                            public void onConnectionFailed(ConnectionResult result) {
                                Log.d(TAG, "Connection failed. Cause: " + result.toString());
                                if (!result.hasResolution()) {
                                    // Show the localized error dialog
                                    GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(),
                                            getActivity(), 0).show();
                                    return;
                                }
                                // The failure has a resolution. Resolve it.
                                // Called typically when the app is not yet authorized, and an
                                // authorization dialog is displayed to the user.
                                if (!authInProgress) {
                                    try {
                                        Log.d(TAG, "Attempting to resolve failed connection");
                                        authInProgress = true;
                                        result.startResolutionForResult(getActivity(),
                                                REQUEST_OAUTH);
                                    } catch (IntentSender.SendIntentException e) {
                                        Log.d(TAG,
                                                "Exception while starting resolution activity", e);
                                    }
                                }
                            }
                        }
                )
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "connecting...");
        mClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mClient.isConnected()) {
            mClient.disconnect();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_OAUTH) {
            authInProgress = false;
            if (resultCode == Activity.RESULT_OK) {
                // Make sure the app is not already connected or attempting to connect
                if (!mClient.isConnecting() && !mClient.isConnected()) {
                    mClient.connect();
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(AUTH_PENDING, authInProgress);
    }

    /**
     * Create a {@link DataSet} to insert data into the History API, and
     * then create and execute a {@link DataReadRequest} to verify the insertion succeeded.
     * By using an {@link AsyncTask}, we can schedule synchronous calls, so that we can query for
     * data after confirming that our insert was successful. Using asynchronous calls and callbacks
     * would not guarantee that the insertion had concluded before the read request was made.
     * An example of an asynchronous call using a callback can be found in the example
     * on deleting data below.
     */

    private class InsertAndVerifyDataTask extends AsyncTask<Void, Void, Void> {
        protected Void doInBackground(Void... params) {

            // Begin by creating the query.
            DataReadRequest readRequest = queryFitnessData();

            // [START read_dataset]
            // Invoke the History API to fetch the data with the query and await the result of
            // the read request.
            DataReadResult dataReadResult =
                    Fitness.HistoryApi.readData(mClient, readRequest).await(1, TimeUnit.MINUTES);
            // [END read_dataset]

            // For the sake of the sample, we'll print the data so we can see what we just added.
            // In general, logging fitness information should be avoided for privacy reasons.
            printData(dataReadResult);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            workout_info.setText("Heart: " + bpm_avg);
            stepIndicatorItem.setWeight(steps);
            distanceIndicatorItem.setWeight(distance);
            calorieIndicatorItem.setWeight(calories);

            int percentage = steps + distance + calories;
            mWheelIndicatorView.setFilledPercent(percentage);
            mWheelIndicatorView.notifyDataSetChanged();
            mWheelIndicatorView.startItemsAnimation();

            //update db
            ContentValues contentValues = new ContentValues();

        }
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        int i = 0;
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.workout_info:
                    workout_info.startAnimation(fadeoutAnimation);
                    switch (i % 3) {
                        case 0:
                            workout_info.setText("Calories: " + calories);
                            break;
                        case 1:
                            workout_info.setText("Distance: " + distance);
                            break;
                        case 2:
                            workout_info.setText("Heart: " + bpm_avg);
                            break;
                    }
                    workout_info.startAnimation(fadeinAnimation);
                    i++;
                    break;
            }
        }
    };

    /**
     * Return a {@link DataReadRequest} for all step count changes in the past week.
     */

    private DataReadRequest queryFitnessData() {
        // [START build_read_data_request]
        // Setting a start and end date using a range of 1 week before this moment.
        Calendar cal = Calendar.getInstance();
        Date now = new Date();
        cal.setTime(now);
        long endTime = cal.getTimeInMillis();
        cal.add(Calendar.DAY_OF_YEAR, -1);
        long startTime = cal.getTimeInMillis();

        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        Log.d(TAG, "Range Start: " + dateFormat.format(startTime));
        Log.d(TAG, "Range End: " + dateFormat.format(endTime));

        DataReadRequest readRequest = new DataReadRequest.Builder()
                // The data request can specify multiple data types to return, effectively
                // combining multiple data queries into one call.
                // In this example, it's very unlikely that the request is for several hundred
                // datapoints each consisting of a few steps and a timestamp.  The more likely
                // scenario is wanting to see how many steps were walked per day, for 7 days.
                .aggregate(DataType.TYPE_HEART_RATE_BPM, DataType.AGGREGATE_HEART_RATE_SUMMARY)
                .aggregate(DataType.TYPE_CALORIES_EXPENDED, DataType.AGGREGATE_CALORIES_EXPENDED)
                .aggregate(DataType.TYPE_DISTANCE_DELTA, DataType.AGGREGATE_DISTANCE_DELTA)
                .aggregate(DataType.TYPE_STEP_COUNT_DELTA, DataType.AGGREGATE_STEP_COUNT_DELTA)
                        // Analogous to a "Group By" in SQL, defines how data should be aggregated.
                        // bucketByTime allows for a time span, whereas bucketBySession would allow
                        // bucketing by "sessions", which would need to be defined in code.
                .bucketByTime(1, TimeUnit.DAYS)
                .setTimeRange(startTime, endTime, TimeUnit.MILLISECONDS)
                .build();
        // [END build_read_data_request]

        return readRequest;
        }

/**
     * Log a record of the query result. It's possible to get more constrained data sets by
     * specifying a data source or data type, but for demonstrative purposes here's how one would
     * dump all the data. In this sample, logging also prints to the device screen, so we can see
     * what the query returns, but your app should not log fitness information as a privacy
     * consideration. A better option would be to dump the data you receive to a local data
     * directory to avoid exposing it to other applications.
     */

    private void printData(DataReadResult dataReadResult) {
        // [START parse_read_data_result]
        // If the DataReadRequest object specified aggregated data, dataReadResult will be returned
        // as buckets containing DataSets, instead of just DataSets.
        if (dataReadResult.getBuckets().size() > 0) {
            Log.d(TAG, "Number of returned buckets of DataSets is: "
                    + dataReadResult.getBuckets().size());
            for (Bucket bucket : dataReadResult.getBuckets()) {
                List<DataSet> dataSets = bucket.getDataSets();
                for (DataSet dataSet : dataSets) {
                    dumpDataSet(dataSet);
                }
            }
        } else if (dataReadResult.getDataSets().size() > 0) {
            Log.d(TAG, "Number of returned DataSets is: "
                    + dataReadResult.getDataSets().size());
            for (DataSet dataSet : dataReadResult.getDataSets()) {
                dumpDataSet(dataSet);
            }
        }
        // [END parse_read_data_result]
    }

    // [START parse_dataset]
    private void dumpDataSet(DataSet dataSet) {
        Log.d(TAG, "Data returned for Data type: " + dataSet.getDataType().getName());
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

        for (DataPoint dp : dataSet.getDataPoints()) {
            /*Log.d(TAG, "Data point:");
            Log.d(TAG, "\tType: " + dp.getDataType().getName());
            Log.d(TAG, "\tStart: " + dateFormat.format(dp.getStartTime(TimeUnit.MILLISECONDS)));
            Log.d(TAG, "\tEnd: " + dateFormat.format(dp.getEndTime(TimeUnit.MILLISECONDS)));*/
            for(Field field : dp.getDataType().getFields()) {
                switch (dataSet.getDataType().getName()) {
                    case Constant.BPM_FEILD:
                        switch (field.getName()) {
                            case Constant.BPM_AVG:
                                bpm_avg = (int) dp.getValue(field).asFloat();
                                break;
                            case Constant.BPM_MAX:
                                bpm_max = (int) dp.getValue(field).asFloat();
                                break;
                            case Constant.BPM_MIN:
                                bpm_min = (int) dp.getValue(field).asFloat();
                                break;
                        }
                        break;
                    case Constant.CALORIES_FEILD:
                        calories = (int) dp.getValue(field).asFloat();
                        break;
                    case Constant.DISTANCE_FEILD:
                        distance = (int) dp.getValue(field).asFloat();
                        break;
                    case Constant.STEPS_FEILD:
                        steps = (int) dp.getValue(field).asInt();
                        break;
                }
                Log.d(TAG, "\tField: " + field.getName() +
                        " Value: " + dp.getValue(field));
            }
        }
    }
    // [END parse_dataset]

}
