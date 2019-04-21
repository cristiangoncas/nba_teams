package com.cristiangoncas.repository.models;

import java.util.List;

public class ListTeamsResponse extends ClientResponse {
    private List<Team> teamList;

    public ListTeamsResponse(boolean success, String message) {
        super(success, message);
    }

    public ListTeamsResponse(boolean success, List<Team> teamList) {
        super(success, null);
        this.teamList = teamList;
    }

    public List<Team> getTeamList() {
        return teamList;
    }
}
