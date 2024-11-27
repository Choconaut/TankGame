package com.example.tankgame.gameobject.tank.team;

import java.util.HashMap;
import java.util.Map;

/**
 *  This class manages the teams in the game. It is responsible for creating, removing, and updating teams.
 *  It also checks if there is only one team left in the game.
 *
 *  It utilizes a HashMap to store the teams, where the key is the team name and the value is the team object.
 *  Currently, the AIDifficulty class does not target multiple teams, so only 2 teams are created in the game.
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
        checkLastTeamStanding();

        for (Team team : teams.values()) {
            team.update();

            if (team.getTeam().isEmpty()) {
                System.out.println("The team " + team.getName() + " has been eliminated!");
                removeTeam(team.getName());
            }
        }
    }

    // Check if there is only one team left
    public Boolean checkLastTeamStanding(){
        if (teams.size() == 1) {
            System.out.println("The winner is: " + teams.values().iterator().next().getName());
            return true;
        }
        return false;
    }

}
