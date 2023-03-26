package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class ShieldBash extends Rune {
    public static final String name = "Shield Bash";
    public static final RunePath path = RunePath.Resolve;
    public static final int column = 1;
    public static final int row = 2;

    public ShieldBash() {
        super(name, path, column, row);
    }
    //not implemented yet!

    @Override
    public Rune makeCopy() {
        return new ShieldBash();
    }
}