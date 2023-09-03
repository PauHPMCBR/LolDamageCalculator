package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class IngeniousHunter extends Rune {
    public static final String name = "Ingenious Hunter";
    public static final RunePath path = RunePath.Domination;
    public static final int column = 3;
    public static final int row = 1;

    int stacks;

    public IngeniousHunter(int stacks) {
        super(name, path, column, row);
        extraVariableName = "Ingenious Stacks (0-5)";
        this.stacks = Math.min(stacks, 5);
    }

    public void specialStats() {
        cs.champion.ITEM_HASTE += 20 + 6*stacks;
    }

    @Override
    public Rune makeCopy() {
        return new IngeniousHunter(stacks);
    }
}