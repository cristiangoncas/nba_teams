package com.cristiangoncas.nbateams.data.mappers;

import com.cristiangoncas.nbateams.data.models.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamMapper {

    public static List<Team> convertFromRepositoryModel(List<com.cristiangoncas.repository.models.Team> teams) {
        return convertFromRepositoryListTeams(teams);
    }

    private static List<Team> convertFromRepositoryListTeams(List<com.cristiangoncas.repository.models.Team> teams) {
        List<Team> dataListTeams = new ArrayList<>();
        for (com.cristiangoncas.repository.models.Team team : teams) {
            dataListTeams.add(new Team(team.getId(), team.getFullName(), team.getWins(), team.getLosses()));
        }
        return dataListTeams;
    }

}
