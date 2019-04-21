package com.cristiangoncas.nbateams.data.models;

import java.util.List;

public class TeamAndPlayers extends Team {

    private List<Player> players;

    public TeamAndPlayers(int id, String fullName, int wins, int losses, List<Player> players) {
        super(id, fullName, wins, losses);
        this.players = players;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
