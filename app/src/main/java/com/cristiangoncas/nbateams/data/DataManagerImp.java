package com.cristiangoncas.nbateams.data;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.widget.Toast;

import com.cristiangoncas.nbateams.data.mappers.TeamAndPlayersMapper;
import com.cristiangoncas.nbateams.data.mappers.TeamMapper;
import com.cristiangoncas.nbateams.data.models.SortBy;
import com.cristiangoncas.nbateams.data.models.Team;
import com.cristiangoncas.nbateams.data.models.TeamAndPlayers;
import com.cristiangoncas.repository.models.ListTeamsResponse;
import com.cristiangoncas.repository.network.Client;
import com.cristiangoncas.repository.network.ClientCallback;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DataManagerImp implements DataManager {

    private Context context;
    private static DataManager INSTANCE;
    private Client repositoryClient;

    private DataManagerImp(Application application) {
        context = application;
        repositoryClient = new Client();
    }

    public static DataManager getInstance(Application application) {
        if (INSTANCE == null) {
            INSTANCE = new DataManagerImp(application);
        }
        return INSTANCE;
    }

    @Override
    public void getListTeams(final MutableLiveData<List<Team>> mutableLiveData) {
        repositoryClient.getListTeams((Application) context,
                new ClientCallback<ListTeamsResponse>() {
                    @Override
                    public void response(ListTeamsResponse clientResponse) {
                        if (clientResponse.isSuccess()) {
                            List<Team> teamList = TeamMapper.convertFromRepositoryModel(clientResponse.getTeamList());
                            sortListTeams(SortBy.ALPH_DESC, teamList);
                            mutableLiveData.postValue(teamList);
                        } else {
                            Toast.makeText(context, clientResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void getTeamAndPlayers(final int teamId, final MutableLiveData<TeamAndPlayers> mutableLiveData) {
        repositoryClient.getListTeams((Application) context,
                new ClientCallback<ListTeamsResponse>() {
                    @Override
                    public void response(ListTeamsResponse clientResponse) {
                        if (clientResponse.isSuccess()) {
                            List<com.cristiangoncas.repository.models.Team> teams = clientResponse.getTeamList();
                            int counter = 0;
                            com.cristiangoncas.repository.models.Team team;
                            do {
                                team = teams.get(counter);
                                counter++;
                            } while (team.getId() != teamId);
                            TeamAndPlayers teamAndPlayers = TeamAndPlayersMapper.convertFromRepositoryData(team);
                            mutableLiveData.postValue(teamAndPlayers);
                        } else {
                            Toast.makeText(context, clientResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void getSortedTeamList(SortBy sortBy, MutableLiveData<List<Team>> mutableLiveData) {
        List<Team> teams = mutableLiveData.getValue();
        if (teams != null && !teams.isEmpty()) {
            sortListTeams(sortBy, teams);
            mutableLiveData.postValue(teams);
        }
    }

    private void sortListTeams(final SortBy sortBy, List<Team> teams) {
        Collections.sort(teams, new Comparator<Team>() {
            @Override
            public int compare(Team t1, Team t2) {
                switch (sortBy) {
                    default:
                    case ALPH_DESC:
                        return t1.getFullName().compareTo(t2.getFullName());
                    case ALPH_ASC:
                        return t2.getFullName().compareTo(t1.getFullName());
                    case WIN:
                        return Integer.valueOf(t2.getWins()) - Integer.valueOf(t1.getWins());
                    case LOSSES:
                        return Integer.valueOf(t2.getLosses()) - Integer.valueOf(t1.getLosses());
                }
            }
        });
    }
}
