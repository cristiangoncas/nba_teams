package com.cristiangoncas.repository.network;

import com.cristiangoncas.repository.models.Team;
import com.cristiangoncas.repository.utils.ConfigConstants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Calls {

    @GET(ConfigConstants.DATA_PATH)
    Call<List<Team>> getTeamList();
}
