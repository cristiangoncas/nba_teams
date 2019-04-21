package com.cristiangoncas.nbateams.ui.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.cristiangoncas.nbateams.R;
import com.cristiangoncas.nbateams.data.models.Player;
import com.cristiangoncas.nbateams.databinding.ItemPlayerBinding;

import java.util.List;

public class ListPlayersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Player> players;

    public ListPlayersAdapter(List<Player> players) {
        this.players = players;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemPlayerBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_player, viewGroup, false);
        return new PlayerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        Player player = players.get(position);
        PlayerViewHolder playerViewHolder = (PlayerViewHolder) viewHolder;
        playerViewHolder.bind(player);
    }

    @Override
    public int getItemCount() {
        return (players != null) ? players.size() : 0;
    }

    public void updateListPlayers(List<Player> players) {
        this.players = players;
    }

    class PlayerViewHolder extends RecyclerView.ViewHolder {

        ItemPlayerBinding itemPlayerBinding;

        PlayerViewHolder(ItemPlayerBinding itemPlayerBinding) {
            super(itemPlayerBinding.getRoot());
            this.itemPlayerBinding = itemPlayerBinding;
        }

        void bind(Player player) {
            itemPlayerBinding.setPlayer(player);
        }
    }
}
