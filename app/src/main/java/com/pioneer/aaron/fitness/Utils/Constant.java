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
    String TAG_TRA_DIALOG = "track_dialog_fragment";

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

    //playground keys
    String PLAYGROUND_NUM = "playground_num";
    String PLAYGROUND_TYPE = "playground_type";
    String PLAYGROUND_MA = "playground_ma";
    String PLAYGROUND_TRACK = "playground_track";
    String PLAYGROUND_BH = "playground_bh";
    String PLAYGROUND_PRICE = "playground_price";
    String PLAYGROUND_BRIEF = "playground_brief";
    String PLAYGROUND_THUMBNAIL = "playground_thumnnail";
    String PLAYGROUND_ID = "playground_id";
    String PLAYGROUND_BOOLEANS = "playground_booleans";
    String PLAYGROUND_LOCATION = "playground_location";

    //people keys
    String P_ID = "p_id";
    String P_THUMBNAIL = "p_thumbnail";
    String P_SLOGAN = "p_slogan";
    String P_DISTANCE = "p_distance";
    String P_FANS = "p_fans";
    String P_ACHIEVE = "p_achieve";
    String P_TRACK_1 = "p_track_1";
    String P_TRACK_2 = "p_track_2";
    String P_TRACK_3 = "p_track_3";

}
