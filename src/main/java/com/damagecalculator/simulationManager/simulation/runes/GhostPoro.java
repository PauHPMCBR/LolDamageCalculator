package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class GhostPoro extends Rune {
    public static final String name = "Ghost Poro";
    public static final RunePath path = RunePath.Domination;
    public static final int column = 2;
    public static final int row = 1;

    int stacks;

    public GhostPoro(int stacks) {
        super(name, path, column, row);
        extraVariableName = "Ghost Poros";
        this.stacks = Math.min(stacks, 10);
    }

    public void specialStats() {
        if (cs.champion.getAdaptive().equals(DamageType.physicalDmg)) {
            cs.champion.BONUS_AD += 1.2 * stacks;
            if (stacks == 10) cs.champion.BONUS_AD += 6;
        } else {
            cs.champion.AP += 2 * stacks;
            if (stacks == 10) cs.champion.AP += 10;
        }
    }

    @Override
    public Rune makeCopy() {
        return new GhostPoro(stacks);
    }
}
