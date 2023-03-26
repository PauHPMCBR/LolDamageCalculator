package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class GatheringStorm extends Rune {
    public static final String name = "Gathering Storm";
    public static final RunePath path = RunePath.Sorcery;
    public static final int column = 3;
    public static final int row = 2;

    int stacks;

    public GatheringStorm(int stacks) {
        super(name, path, column, row);
        extraVariableName = "Gathering Stacks";
        this.stacks = stacks;
    }

    public void specialStats() {
        if (cs.champion.getAdaptive().equals(DamageType.physicalDmg))
            cs.champion.BONUS_AD += 2.4 * stacks * (stacks + 1);
        else
            cs.champion.AP += 4 * stacks * (stacks + 1);
    }

    @Override
    public Rune makeCopy() {
        return new GatheringStorm(stacks);
    }
}
