package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class Overgrowth extends Rune {
    public static final String name = "Overgrowth";
    public static final RunePath path = RunePath.Resolve;
    public static final int column = 3;
    public static final int row = 0;

    int stacks;

    public Overgrowth(int stacks) {
        super(name, path, column, row);
        extraVariableName = "Overgrowth Stacks";
        this.stacks = stacks;
    }

    public void specialStats() {
        owner.BONUS_HP += 3 * stacks;
        if (stacks >= 15) owner.BONUS_HP += owner.getMaxHP() * 0.035;
    }

    @Override
    public Rune makeCopy() {
        return new Overgrowth(stacks);
    }
}