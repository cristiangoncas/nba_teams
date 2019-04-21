package com.cristiangoncas.nbateams.ui.views;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cristiangoncas.nbateams.R;
import com.cristiangoncas.nbateams.data.models.Player;
import com.cristiangoncas.nbateams.data.models.TeamAndPlayers;
import com.cristiangoncas.nbateams.databinding.FragmentTeamPageBinding;
import com.cristiangoncas.nbateams.ui.adapters.ListPlayersAdapter;
import com.cristiangoncas.nbateams.utils.Constants;
import com.cristiangoncas.nbateams.viewmodels.TeamViewModel;
import com.cristiangoncas.nbateams.viewmodels.TeamViewModelImp;

import java.util.ArrayList;

public class TeamAndPlayersFragment extends Fragment {

    public static String TAG = "TeamAndPlayersFragment";
    TeamViewModel teamViewModel;
    ListPlayersAdapter adapter;

    private int teamId;
    private FragmentTeamPageBinding binding;

    public static TeamAndPlayersFragment newInstance(int teamId) {
        Bundle args = new Bundle();
        args.putInt(Constants.TEAM_ID, teamId);
        TeamAndPlayersFragment fragment = new TeamAndPlayersFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().containsKey(Constants.TEAM_ID)) {
            teamId = getArguments().getInt(Constants.TEAM_ID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_team_page, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getActivity() != null) {
            initializeTeamViewModel();
            setupRecyclerView();
            subscribeToViewModelLiveData();
        }
    }

    private void initializeTeamViewModel() {
        assert getActivity() != null;
        teamViewModel = ViewModelProviders.of(getActivity()).get(TeamViewModelImp.class);
    }

    private void subscribeToViewModelLiveData() {
        teamViewModel.getTeamAndPlayers(teamId).observe(this, new Observer<TeamAndPlayers>() {
            @Override
            public void onChanged(@Nullable TeamAndPlayers teamAndPlayers) {
                if (teamAndPlayers != null) {
                    binding.setTeam(teamAndPlayers);
                    adapter.updateListPlayers(teamAndPlayers.getPlayers());
                    binding.setPlayersAdapter(adapter);
                }
            }
        });
    }

    private void setupRecyclerView() {
        adapter = new ListPlayersAdapter(new ArrayList<Player>());
    }
}
