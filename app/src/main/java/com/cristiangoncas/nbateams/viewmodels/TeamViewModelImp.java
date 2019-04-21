package com.cristiangoncas.nbateams.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.cristiangoncas.nbateams.data.DataManager;
import com.cristiangoncas.nbateams.data.DataManagerImp;
import com.cristiangoncas.nbateams.data.models.SortBy;
import com.cristiangoncas.nbateams.data.models.Team;
import com.cristiangoncas.nbateams.data.models.TeamAndPlayers;
import com.cristiangoncas.nbateams.ui.views.ListTeamsFragment;
import com.cristiangoncas.nbateams.ui.views.TeamAndPlayersFragment;

import java.util.List;

public class TeamViewModelImp extends AndroidViewModel implements TeamViewModel {

    DataManager dataManager;
    MutableLiveData<Integer> teamSelected;
    MutableLiveData<List<Team>> teamsListLiveData;
    public MutableLiveData<TeamAndPlayers> teamAndPlayersLiveData;
    String currentFragment = "";

    public TeamViewModelImp(@NonNull Application application) {
        super(application);
        dataManager = DataManagerImp.getInstance(application);
        teamSelected = new MutableLiveData<>();
        teamsListLiveData = new MutableLiveData<>();
        teamAndPlayersLiveData = new MutableLiveData<>();
    }

    @Override
    public void activityStarting() {
        currentFragment = ListTeamsFragment.TAG;
    }

    @Override
    public void onBackPressed() {
        currentFragment = ListTeamsFragment.TAG;
    }

    @Override
    public LiveData<Integer> subscribeToTeamSelected() {
        return teamSelected;
    }

    @Override
    public LiveData<List<Team>> getListTeams() {
        dataManager.getListTeams(teamsListLiveData);
        return teamsListLiveData;
    }

    @Override
    public LiveData<TeamAndPlayers> getTeamAndPlayers(int teamId) {
        dataManager.getTeamAndPlayers(teamId, teamAndPlayersLiveData);
        return teamAndPlayersLiveData;
    }

    @Override
    public void teamSelected(int teamId) {
        currentFragment = TeamAndPlayersFragment.TAG;
        teamSelected.postValue(teamId);
    }

    @Override
    public void sortListTeams(SortBy sortBy) {
        dataManager.getSortedTeamList(sortBy, teamsListLiveData);
    }
}
