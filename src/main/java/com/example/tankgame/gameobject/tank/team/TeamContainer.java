package com.example.tankgame.gameobject.tank.team;

import java.util.HashMap;
import java.util.Map;

/**
 *  Should a container be used to create teams?
 */
public class TeamContainer {
    private final Map<String, Team> teams = new HashMap<>();

    public Team createTeam(String name) {
        Team team = new Team(name);
        teams.put(name, new Team(name));
        return team;
    }

    public Team getTeam(String name) {
        return teams.get(name);
    }

    public void removeTeam(String name) {
        teams.remove(name);
    }

    // For each team, update the team
    public void updateTeams() {
        for (Team team : teams.values()) {
            team.update();
        }
    }

}
