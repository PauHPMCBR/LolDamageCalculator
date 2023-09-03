package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class UltimateHunter extends Rune {
    public static final String name = "Ultimate Hunter";
    public static final RunePath path = RunePath.Domination;
    public static final int column = 3;
    public static final int row = 3;

    int stacks;

    public UltimateHunter(int stacks) {
        super(name, path, column, row);
        extraVariableName = "Ultimate Stacks (0-5)";
        this.stacks = Math.min(stacks, 5);
    }

    public void specialStats() {
        cs.champion.ULTIMATE_HASTE += 6 + 5*stacks;
    }

    @Override
    public Rune makeCopy() {
        return new UltimateHunter(stacks);
    }
}