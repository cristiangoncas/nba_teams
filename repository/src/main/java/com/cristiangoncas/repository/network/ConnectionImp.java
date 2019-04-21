package com.cristiangoncas.repository.network;

import android.content.Context;

import com.cristiangoncas.repository.models.Team;

import java.util.List;

import retrofit2.Call;

class ConnectionImp implements Connection {

    private ConnectionProvider getConnectionProvider(Context context) {
        return new ConnectionProvider(context);
    }

    @Override
    public Call<List<Team>> callGetListTeam(Context context) {
        ConnectionProvider connectionProvider = getConnectionProvider(context);
        return connectionProvider.getRetrofit().create(Calls.class).getTeamList();
    }
}
