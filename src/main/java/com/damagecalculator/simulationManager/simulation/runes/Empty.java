package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class Empty extends Rune {
    public static final String name = "Empty";
    public static final RunePath path = RunePath.Precision;
    public static final int column = 0;
    public static final int row = 0;

    public Empty() {
        super(name, path, column, row);
    }

    @Override
    public Rune makeCopy() {
        return new Empty();
    }
}