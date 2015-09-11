package com.pioneer.aaron.fitness.Adapters;

/**
 * Created by Aaron on 9/6/15.
 */
public class GamesItemModel {
    public int game_thumbnail;
    public String game_name;
    public String game_participant;
    public String game_countdown;

    public GamesItemModel(int game_thumbnail, String game_name, String game_participant, String game_countdown) {
        this.game_thumbnail = game_thumbnail;
        this.game_name = game_name;
        this.game_participant = game_participant;
        this.game_countdown = game_countdown;
    }
}
