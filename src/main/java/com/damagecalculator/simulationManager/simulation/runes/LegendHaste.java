package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class LegendHaste extends Rune {
    public static final String name = "Legend: Haste";
    public static final RunePath path = RunePath.Precision;
    public static final int column = 2;
    public static final int row = 1;

    int stacks;

    public LegendHaste(int stacks) {
        super(name, path, column, row);
        extraVariableName = "Haste Stacks (0-10)";

        this.stacks = stacks;
    }

    public void specialStats() {
        cs.champion.AH += 1.5f*Math.min(stacks, 10);
    }

    @Override
    public Rune makeCopy() {
        return new LegendHaste(stacks);
    }
}