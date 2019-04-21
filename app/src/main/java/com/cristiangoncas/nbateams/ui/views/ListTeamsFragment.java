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
import com.cristiangoncas.nbateams.data.models.Team;
import com.cristiangoncas.nbateams.databinding.FragmentListTeamsBinding;
import com.cristiangoncas.nbateams.ui.adapters.ListTeamsAdapter;
import com.cristiangoncas.nbateams.viewmodels.TeamViewModel;
import com.cristiangoncas.nbateams.viewmodels.TeamViewModelImp;

import java.util.ArrayList;
import java.util.List;

public class ListTeamsFragment extends Fragment {

    public static String TAG = "ListTeamsFragment";
    TeamViewModel teamViewModel;
    ListTeamsAdapter adapter;

    private FragmentListTeamsBinding binding;

    public static ListTeamsFragment newInstance() {
        Bundle args = new Bundle();
        ListTeamsFragment fragment = new ListTeamsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_teams, container, false);
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
        teamViewModel.getListTeams().observe(this, new Observer<List<Team>>() {
            @Override
            public void onChanged(@Nullable List<Team> teams) {
                adapter.updateListTeams(teams);
                binding.setTeamsAdapter(adapter);
            }
        });
    }

    private void setupRecyclerView() {
        adapter = new ListTeamsAdapter(new ArrayList<Team>(), teamViewModel);
    }
}
