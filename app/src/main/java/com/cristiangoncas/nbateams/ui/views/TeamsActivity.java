package com.cristiangoncas.nbateams.ui.views;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.cristiangoncas.nbateams.R;
import com.cristiangoncas.nbateams.data.models.SortBy;
import com.cristiangoncas.nbateams.viewmodels.TeamViewModel;
import com.cristiangoncas.nbateams.viewmodels.TeamViewModelImp;

public class TeamsActivity extends AppCompatActivity {

    TeamViewModel teamViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeTeamViewModel();
        observeViewModelLiveData();
        showListTeamsView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sort_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.alph_desc:
                teamViewModel.sortListTeams(SortBy.ALPH_DESC);
                break;
            case R.id.alph_asc:
                teamViewModel.sortListTeams(SortBy.ALPH_ASC);
                break;
            case R.id.wins:
                teamViewModel.sortListTeams(SortBy.WIN);
                break;
            case R.id.losses:
                teamViewModel.sortListTeams(SortBy.LOSSES);
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        actionBarChangeVisibility(false);
    }

    private void initializeTeamViewModel() {
        teamViewModel = ViewModelProviders.of(this).get(TeamViewModelImp.class);
        teamViewModel.activityStarting();
    }

    private void observeViewModelLiveData() {
        teamViewModel.subscribeToTeamSelected().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer teamId) {
                teamSelected(teamId);
            }
        });
    }

    private void teamSelected(int teamId) {
        actionBarChangeVisibility(true);
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.findFragmentByTag(TeamAndPlayersFragment.TAG) == null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content, TeamAndPlayersFragment.newInstance(teamId), TeamAndPlayersFragment.TAG)
                    .addToBackStack(null)
                    .commit();
        }
    }

    public void showListTeamsView() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(ListTeamsFragment.TAG);
        if (fragment == null) {
            fragmentManager.beginTransaction()
                    .add(R.id.content, ListTeamsFragment.newInstance(), ListTeamsFragment.TAG)
                    .commit();
        } else {
            fragmentManager.beginTransaction()
                    .replace(R.id.content, fragment, ListTeamsFragment.TAG)
                    .commit();
        }
    }

    private void actionBarChangeVisibility(boolean hide) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            if (hide) {
                actionBar.hide();
            } else {
                actionBar.show();
            }
        }
    }
}
