package com.pioneer.aaron.fitness.Adapters;

/**
 * Created by Aaron on 9/6/15.
 */
public class PlaygroundItemModel {
    public int playground_thumbnail;
    public String playground_id;
    public boolean[] tag_Booleans;
    /*run walk swim yoga bike badminton exercise*/
    public String playground_location;

    public PlaygroundItemModel(int playground_thumbnail, String playgroun_id, boolean[] tag_Booleans, String location) {
        this.playground_thumbnail = playground_thumbnail;
        this.playground_id = playgroun_id;
        this.tag_Booleans = tag_Booleans;
        this.playground_location= location;
    }
}
