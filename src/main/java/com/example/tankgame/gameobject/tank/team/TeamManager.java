package com.example.tankgame.gameobject.tank.team;

import java.util.HashMap;
import java.util.Map;

/**
 *  Should a container be used to create teams?
 */
public class TeamManager {
    private final Map<String, Team> teams = new HashMap<>();

    public void createTeam(String name) {
        teams.put(name, new Team(name));
    }

    public Team getTeam(String name) {
        return teams.get(name);
    }

    public void removeTeam(String name) {
        teams.remove(name);
    }

    // For each team, update the team
    public void updateTeams() {
        checkTeams();

        for (Team team : teams.values()) {
            team.update();

            if (team.getTeam().isEmpty()) {
                System.out.println("The team " + team.getName() + " has been eliminated!");
                removeTeam(team.getName());
            }
        }
    }

    // Check if there is only one team left
    public Boolean checkTeams (){
        if (teams.size() == 1) {
            System.out.println("The winner is: " + teams.values().iterator().next().getName());
            return true;
        }
        return false;
    }

}
