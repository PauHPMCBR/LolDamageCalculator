package simulationManager.simulation.runes;

import simulationManager.simulation.Rune;
import simulationManager.simulation.RunePath;

public class SuddenImpact extends Rune {
    public static final String name = "Sudden Impact";
    public static final RunePath path = RunePath.domination;
    public static final int column = 1;
    public static final int row = 2;

    boolean isActive;
    float timeActivated;

    public SuddenImpact() {
        super(name, path, column, row);
        rune_cooldown = 4;

        isActive = false;
        timeActivated = 0;
    }

    public void extraDmg() { //extremely inaccurate, supposing every ability is a dash
        if (!isActive && canUse()) { //the extra comparator is to see that the rune is NOT active atm
            isActive = true;
            timeActivated = cs.time;
            owner.LETHALITY += 9;
            owner.MAGIC_PEN += 7;
        }
        else if (cs.time > timeActivated + 5) {
            putOnCooldown();
            isActive = false;
            owner.LETHALITY -= 9;
            owner.MAGIC_PEN -= 7;
        }
    }

    @Override
    public Rune makeCopy() {
        return new SuddenImpact();
    }
}