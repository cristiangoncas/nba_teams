package com.cristiangoncas.nbateams.data.models;

public class Team {

    private int id;
    private String fullName;
    private String wins;
    private String losses;

    public Team(int id, String fullName, int wins, int losses) {
        this.id = id;
        this.fullName = fullName;
        this.wins = String.valueOf(wins);
        this.losses = String.valueOf(losses);
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getWins() {
        return wins;
    }

    public String getLosses() {
        return losses;
    }
}
