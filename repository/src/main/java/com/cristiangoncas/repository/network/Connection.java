package com.cristiangoncas.repository.network;

import android.content.Context;

import com.cristiangoncas.repository.models.Team;

import java.util.List;

import retrofit2.Call;

public interface Connection {

    Call<List<Team>> callGetListTeam(Context context);
}
