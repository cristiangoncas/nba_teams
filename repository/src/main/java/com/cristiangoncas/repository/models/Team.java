package com.cristiangoncas.repository.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Team {

    private int id;
    @SerializedName("full_name")
    private String fullName;
    private int wins;
    private int losses;
    private List<Player> players;

    public Team(int id, String fullName, int wins, int losses, List<Player> players) {
        this.id = id;
        this.fullName = fullName;
        this.wins = wins;
        this.losses = losses;
        this.players = players;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
