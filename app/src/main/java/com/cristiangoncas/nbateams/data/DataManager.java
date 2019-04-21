package com.cristiangoncas.nbateams.data;

import android.arch.lifecycle.MutableLiveData;

import com.cristiangoncas.nbateams.data.models.SortBy;
import com.cristiangoncas.nbateams.data.models.Team;
import com.cristiangoncas.nbateams.data.models.TeamAndPlayers;

import java.util.List;

public interface DataManager {

    void getListTeams(MutableLiveData<List<Team>> teamStatisticsLiveData);

    void getTeamAndPlayers(int teamId, final MutableLiveData<TeamAndPlayers> mutableLiveData);

    void getSortedTeamList(SortBy sortBy, MutableLiveData<List<Team>> teamStatisticsLiveData);
}
