package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class Demolish extends Rune {
    public static final String name = "Demolish";
    public static final RunePath path = RunePath.Resolve;
    public static final int column = 1;
    public static final int row = 0;

    public Demolish() {
        super(name, path, column, row);
    }

    @Override
    public Rune makeCopy() {
        return new Demolish();
    }
}