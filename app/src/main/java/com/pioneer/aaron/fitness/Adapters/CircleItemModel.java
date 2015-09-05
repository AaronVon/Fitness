package com.pioneer.aaron.fitness.Adapters;


/**
 * Created by Aaron on 9/5/15.
 */
public class CircleItemModel {
    protected int user_thumbnail;
    protected String user_id;
    protected boolean tag_run, tag_walk, tag_swim;

    public CircleItemModel(int user_thumbnail, String user_id, boolean tag_run, boolean tag_walk, boolean tag_swim) {
        this.user_thumbnail = user_thumbnail;
        this.user_id = user_id;
        this.tag_run = tag_run;
        this.tag_walk = tag_walk;
        this.tag_swim = tag_swim;
    }
}
