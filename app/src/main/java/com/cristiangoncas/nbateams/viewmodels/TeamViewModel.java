package com.cristiangoncas.nbateams.viewmodels;

import android.arch.lifecycle.LiveData;

import com.cristiangoncas.nbateams.data.models.SortBy;
import com.cristiangoncas.nbateams.data.models.Team;
import com.cristiangoncas.nbateams.data.models.TeamAndPlayers;

import java.util.List;

public interface TeamViewModel {

    void activityStarting();

    void onBackPressed();

    LiveData<Integer> subscribeToTeamSelected();

    LiveData<List<Team>> getListTeams();

    LiveData<TeamAndPlayers> getTeamAndPlayers(int teamId);

    void teamSelected(int teamId);

    void sortListTeams(SortBy sortBy);
}
