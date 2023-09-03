package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class ManaflowBand extends Rune {
    public static final String name = "Manaflow Band";
    public static final RunePath path = RunePath.Sorcery;
    public static final int column = 1;
    public static final int row = 1;

    int stacks;

    public ManaflowBand(int stacks) {
        super(name, path, column, row);
        extraVariableName = "Manaflow Stacks (0-10)";
        this.stacks = Math.min(stacks, 10);
    }
    public ManaflowBand() {
        super(name, path, column, row);

        this.stacks = 10;
    }

    public void specialStats() {
        owner.MANA += 25 * stacks;
    }

    @Override
    public Rune makeCopy() {
        return new ManaflowBand(stacks);
    }
}
