package com.cristiangoncas.nbateams.data;

import com.cristiangoncas.repository.models.Player;
import com.cristiangoncas.repository.models.Team;
import com.cristiangoncas.repository.models.ListTeamsResponse;
import com.cristiangoncas.repository.network.Client;
import com.cristiangoncas.repository.network.ClientCallback;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TestDataManagerImp {

    @Captor
    private ArgumentCaptor<ClientCallback<ListTeamsResponse>> callbackCaptor;


    @Mock
    private Client repositoryClient;

    @Mock
    private ClientCallback<ListTeamsResponse> clientCallback;

    private List<Team> listTeams;
    private List<Player> listPlayers;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        listTeams = new ArrayList<>();
        listPlayers = new ArrayList<>();
        listPlayers.add(new Player(0, "First name", "Last name", "Pos", 0));
        listTeams.add(new Team(0, "Team 1", 0, 0, listPlayers));
    }

    @Test
    public void testListTeams() {
//        repositoryClient.getListTeams(eq(RuntimeEnvironment.application), eq(clientCallback));
//
//        verify(repositoryClient, times(1)).getListTeams(eq(RuntimeEnvironment.application), callbackCaptor.capture());
//
//        ClientCallback<ListTeamsResponse> callback = callbackCaptor.getValue();
//        ListTeamsResponse response = new ListTeamsResponse(true, listTeams);
//        callback.response(response);
//        assertEquals(listTeams, response.getTeamList());
    }
}

