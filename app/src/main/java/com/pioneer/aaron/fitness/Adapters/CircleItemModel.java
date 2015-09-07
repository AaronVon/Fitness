package com.pioneer.aaron.fitness.Adapters;


/**
 * Created by Aaron on 9/5/15.
 */
public class CircleItemModel {
    public int user_thumbnail;
    public String user_id;
    public String user_slogan;
    public boolean tag_run, tag_walk, tag_swim;

    public CircleItemModel(int user_thumbnail, String user_id, String user_slogan, boolean tag_run, boolean tag_walk, boolean tag_swim) {
        this.user_thumbnail = user_thumbnail;
        this.user_id = user_id;
        this.user_slogan = user_slogan;
        this.tag_run = tag_run;
        this.tag_walk = tag_walk;
        this.tag_swim = tag_swim;
    }
}
