
package simulationManager.simulation.runes;

import simulationManager.simulation.DamageType;
import simulationManager.simulation.Rune;
import simulationManager.simulation.RunePath;

public class FirstStrike extends Rune {
    public static final String name = "First Strike";
    public static final RunePath path = RunePath.inspiration;
    public static final int column = 0;
    public static final int row = 2;

    boolean isActive;
    float timeActivated;

    public FirstStrike() {
        super(name, path, column, row);
    }

    public void specialStats() {
        rune_cooldown = 25 - 10f/17f*(cs.champion.lvl-1);

        isActive = true;
        timeActivated = cs.time; //activated before first dmg instance
        cs.hasFirstStrike = true;
        cs.firstStrikeRune = this;
    }

    public void extraDmg() {
        if (isActive) {
            if (cs.time > timeActivated + 3) {
                cs.hasFirstStrike = false;
                putOnCooldown();
                isActive = false;
            }
        }
        else {
            if (canUse()) {
                isActive = true;
                timeActivated = cs.time;
                cs.hasFirstStrike = true;
            }
        }
    }

    public void onHit() {
        extraDmg();
    }

    @Override
    public Rune makeCopy() {
        return new FirstStrike();
    }
}