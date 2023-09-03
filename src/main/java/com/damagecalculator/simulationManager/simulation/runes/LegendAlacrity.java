package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class LegendAlacrity extends Rune {
    public static final String name = "Legend: Alacrity";
    public static final RunePath path = RunePath.Precision;
    public static final int column = 2;
    public static final int row = 0;

    int stacks;


    public LegendAlacrity(int stacks) {
        super(name, path, column, row);
        extraVariableName = "Alacrity Stacks (0-10)";

        this.stacks = stacks;
    }

    public void specialStats() {
        cs.champion.BONUS_AS += 3 + 1.5f*Math.min(stacks, 10);
    }

    @Override
    public Rune makeCopy() {
        return new LegendAlacrity(stacks);
    }
}
