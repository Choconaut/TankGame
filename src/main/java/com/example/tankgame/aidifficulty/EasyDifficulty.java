package com.example.tankgame.aidifficulty;

import com.example.tankgame.gameobject.tank.Tank;
import com.example.tankgame.gameobject.tank.team.Team;

/** TODO: angle is not a good way to calculate the direction of the AI tank when
 *  the AI tank is close to the target tank. Perhaps when close, the AI should
 *  strictly focus on one direction, then the other.
 *
 *  This difficulty uses the default values and methods from the AIDifficulty class.
 */

public class EasyDifficulty extends AIDifficulty {


    @Override
    public void execute(Tank AITank, Team targetTanks) {
        super.execute(AITank, targetTanks);

        for (Tank targetTank : targetTanks.getTeam()) {
            while (targetTank.isActive()) {
                fireMissile(AITank, targetTank);
            }
        }
    }

    @Override
    public void fireMissile(Tank AITank, Tank targetTank) {

    }
}