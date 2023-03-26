package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class Guardian extends Rune {
    public static final String name = "Guardian";
    public static final RunePath path = RunePath.Resolve;
    public static final int column = 0;
    public static final int row = 2;

    public Guardian() {
        super(name, path, column, row);
    }

    @Override
    public Rune makeCopy() {
        return new Guardian();
    }
}