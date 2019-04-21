package com.cristiangoncas.repository.network;

import android.app.Application;

import com.cristiangoncas.repository.models.Team;
import com.cristiangoncas.repository.models.ListTeamsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Client {

    private Connection getConnection() {
        return new ConnectionImp();
    }

    public void getListTeams(Application context, final ClientCallback clientCallback) {
        getConnection().callGetListTeam(context.getApplicationContext()).enqueue(new Callback<List<Team>>() {
            @Override
            public void onResponse(Call<List<Team>> call, Response<List<Team>> response) {
                if (response.isSuccessful()) {
                    callClientCallback(new ListTeamsResponse(true, response.body()));
                } else {
                    callClientCallback(new ListTeamsResponse(false, response.message()));
                }
            }

            @Override
            public void onFailure(Call<List<Team>> call, Throwable t) {
                callClientCallback(new ListTeamsResponse(false, t.getMessage()));
            }

            private void callClientCallback(ListTeamsResponse response) {
                clientCallback.response(response);
            }
        });
    }
}
