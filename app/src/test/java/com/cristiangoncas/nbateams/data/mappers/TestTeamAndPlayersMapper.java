package com.cristiangoncas.nbateams.data.mappers;

import com.cristiangoncas.nbateams.data.models.TeamAndPlayers;
import com.cristiangoncas.repository.models.Player;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class TestTeamAndPlayersMapper {

    com.cristiangoncas.repository.models.Team repositoryTeam;

    @Before
    public void setUp() {
        List<Player> listPlayers = new ArrayList<>();
        listPlayers.add(new Player(0, "First name", "Last name", "G", 0));
        listPlayers.add(new Player(1, "First name two", "Last name two", "SG", 1));
        repositoryTeam = new com.cristiangoncas.repository.models.Team(0, "Team name", 40, 20, listPlayers);
    }

    @Test
    public void testConvertFromRepositoryData() {
        TeamAndPlayers teamAndPlayers = TeamAndPlayersMapper.convertFromRepositoryData(repositoryTeam);
        assertEquals(teamAndPlayers.getId(), repositoryTeam.getId());
        assertEquals(teamAndPlayers.getFullName(), repositoryTeam.getFullName());
        assertSame(Integer.valueOf(teamAndPlayers.getWins()), repositoryTeam.getWins());
        assertSame(Integer.valueOf(teamAndPlayers.getLosses()), repositoryTeam.getLosses());
        assertEquals(teamAndPlayers.getPlayers().size(), repositoryTeam.getPlayers().size());
        assertEquals(teamAndPlayers.getPlayers().get(0).getFirstName(), repositoryTeam.getPlayers().get(0).getFirstName());
        assertSame(teamAndPlayers.getPlayers().get(0).getLastName(), repositoryTeam.getPlayers().get(0).getLastName());
        assertSame(Integer.valueOf(teamAndPlayers.getPlayers().get(0).getNumber()), repositoryTeam.getPlayers().get(0).getNumber());
        assertSame(teamAndPlayers.getPlayers().get(0).getPosition(), repositoryTeam.getPlayers().get(0).getPosition());
    }
}
