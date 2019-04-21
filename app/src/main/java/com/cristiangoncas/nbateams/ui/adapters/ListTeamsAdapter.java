package com.cristiangoncas.nbateams.ui.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.cristiangoncas.nbateams.R;
import com.cristiangoncas.nbateams.data.models.Team;
import com.cristiangoncas.nbateams.databinding.ItemTeamBinding;
import com.cristiangoncas.nbateams.viewmodels.TeamViewModel;

import java.util.List;

public class ListTeamsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface TeamSelected {
        void teamSelected(Team team);
    }

    TeamViewModel teamViewModel;
    List<Team> teams;

    public ListTeamsAdapter(List<Team> teams, TeamViewModel teamViewModel) {
        this.teamViewModel = teamViewModel;
        this.teams = teams;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemTeamBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_team, viewGroup, false);

        return new TeamViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        final Team team = teams.get(position);
        final TeamViewHolder teamViewHolder = (TeamViewHolder) viewHolder;
        teamViewHolder.bind(team);
        teamViewHolder.itemTeamBinding.setItemClickListener(new TeamSelected() {
            @Override
            public void teamSelected(Team team) {
                teamViewModel.teamSelected(team.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return (teams != null) ? teams.size() : 0;
    }

    public void updateListTeams(List<Team> teams) {
        this.teams = teams;
    }

    class TeamViewHolder extends RecyclerView.ViewHolder {

        ItemTeamBinding itemTeamBinding;

        TeamViewHolder(ItemTeamBinding itemTeamBinding) {
            super(itemTeamBinding.getRoot());
            this.itemTeamBinding = itemTeamBinding;
        }

        void bind(Team team) {
            itemTeamBinding.setTeam(team);
        }
    }
}
