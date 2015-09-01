package com.pioneer.aaron.fitness.Utils;

/**
 * Created by Aaron on 8/18/15.
 */
public interface Constant {
    //after looking up the Internet, some says the default animation duration is 600ms
    long DRAWER_CLOSE_DELAY_MS = 500;

    String HIS_YEAR = "year";
    String HIS_MONTH = "month";
    String HIS_DAY = "day";
    String TAG_HIS_DIALOG = "history_dialog_fragment";

    //Fitness constant
    String BPM_FEILD = "com.google.heart_rate.summary";
    String BPM_AVG = "average";
    String BPM_MAX = "max";
    String BPM_MIN = "min";
    String CALORIES_FEILD = "com.google.calories.expended";
    String DISTANCE_FEILD = "com.google.distance.delta";
    String STEPS_FEILD = "com.google.step_count.delta";

    //DB
    String DB_NAME = "fitnesshistory.db";
}
