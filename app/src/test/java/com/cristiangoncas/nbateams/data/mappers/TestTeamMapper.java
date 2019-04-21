package com.cristiangoncas.nbateams.data.mappers;

import com.cristiangoncas.nbateams.data.models.Team;
import com.cristiangoncas.repository.models.Player;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class TestTeamMapper {

    List<com.cristiangoncas.repository.models.Team> repositoryTeams;

    @Before
    public void setUp() {
        List<Player> listPlayers = new ArrayList<>();
        listPlayers.add(new Player(0, "First name", "Last name", "G", 0));
        listPlayers.add(new Player(1, "First name two", "Last name two", "SG", 1));
        repositoryTeams = new ArrayList<>();
        repositoryTeams.add(new com.cristiangoncas.repository.models.Team(0, "Team name", 40, 20, listPlayers));
        repositoryTeams.add(new com.cristiangoncas.repository.models.Team(0, "Team name 2", 30, 10, listPlayers));
    }

    @Test
    public void testConvertFromRepositoryData() {
        List<Team> teams = TeamMapper.convertFromRepositoryModel(repositoryTeams);
        assertEquals(teams.size(), repositoryTeams.size());
        assertEquals(teams.get(0).getFullName(), repositoryTeams.get(0).getFullName());
        assertSame(Integer.valueOf(teams.get(0).getWins()), repositoryTeams.get(0).getWins());
        assertSame(Integer.valueOf(teams.get(0).getLosses()), repositoryTeams.get(0).getLosses());
    }
}
