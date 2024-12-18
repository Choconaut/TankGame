package com.example.tankgame.gameobject.tank.team;

import com.example.tankgame.gameobject.tank.Tank;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a group of tanks in the game. Tanks in the same team
 * will not target each other and will work together to defeat tanks in other teams.
 */
public class Team {
    String name;
    private final List<Tank> team = new ArrayList<>();
    private final IntegerProperty activeTankCount = new SimpleIntegerProperty(0);

    public Team (String name) {
        this.name = name;
    }

    public void addTank(Tank tank) {
        team.add(tank);
        updateActiveTankCount();
    }

    public void removeTank(Tank tank) {
        team.remove(tank);
    }

    // if the tank is not active, remove it from the team
    public void update() { team.removeIf(tank -> !tank.isActive()); }

    public List<Tank> getTeam() { return team; }

    public String getName() { return name; }

    public IntegerProperty activeTankCount() {
        return activeTankCount;
    }

    public void updateActiveTankCount() {
        activeTankCount.set((int) team.stream().filter(Tank::isActive).count());
    }

    public int getActiveTankCount() {
        return (int) team.stream().filter(Tank::isActive).count();
    }
}
