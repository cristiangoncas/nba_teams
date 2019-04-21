package com.cristiangoncas.nbateams.data.mappers;

import com.cristiangoncas.nbateams.data.models.Player;
import com.cristiangoncas.nbateams.data.models.TeamAndPlayers;
import com.cristiangoncas.repository.models.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamAndPlayersMapper {

    public static TeamAndPlayers convertFromRepositoryData(Team team) {
        List<Player> players = convertFromRepositoryListPlayer(team.getPlayers());
        return new TeamAndPlayers(team.getId(), team.getFullName(), team.getWins(), team.getLosses(), players);
    }

    private static List<Player> convertFromRepositoryListPlayer(List<com.cristiangoncas.repository.models.Player> players) {
        List<Player> dataListPlayer = new ArrayList<>();
        for (com.cristiangoncas.repository.models.Player player : players) {
            dataListPlayer.add(new Player(player.getFirstName(), player.getLastName(), player.getPosition(), player.getNumber()));
        }
        return dataListPlayer;
    }
}
